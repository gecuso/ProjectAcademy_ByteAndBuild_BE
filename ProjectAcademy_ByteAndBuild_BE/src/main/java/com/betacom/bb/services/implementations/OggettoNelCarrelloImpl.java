package com.betacom.bb.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.betacom.bb.dto.OggettoNelCarrelloDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Carrello;
import com.betacom.bb.models.OggettoNelCarrello;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.ICarrelloRepository;
import com.betacom.bb.repositories.IOggettoNelCarrelloRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.OggettoNelCarrelloReq;
import com.betacom.bb.services.interfaces.IOggettoNelCarrelloService;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OggettoNelCarrelloImpl extends Utilities implements IOggettoNelCarrelloService{
	
	private IOggettoNelCarrelloRepository oncR;
	private ICarrelloRepository carrR;
	private IProdottoRepository prodR;
	
	public OggettoNelCarrelloImpl(IOggettoNelCarrelloRepository oncR, IProdottoRepository prodR, ICarrelloRepository carrR) {
		this.oncR = oncR;
		this.carrR = carrR;
		this.prodR = prodR;
	}
	
	////////////////////////////////
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(OggettoNelCarrelloReq req) throws AcademyException {
		log.debug("create: " + req);
		
		//controllo se esiste già nel database
		Optional<OggettoNelCarrello> oggNelCarr = oncR.findById(req.getId());
		if(!oggNelCarr.isEmpty())
			throw new AcademyException("Oggetto già presente nel database");
		
		//controllo se esiste il carrello
		Optional<Carrello> carrelloRispettivo = carrR.findById(req.getIdCarrello());
		if(carrelloRispettivo.isEmpty())
			throw new AcademyException("Non esiste il carrello da te inserito");
		
		//controllo se c'è già il ONC dentro il carrello
		List<OggettoNelCarrelloDTO> oggettiInQuestoCarrello = getByIdCarrello(req.getIdCarrello());
		for (OggettoNelCarrelloDTO oggettoNelCarrelloDTO : oggettiInQuestoCarrello) {
			if(oggettoNelCarrelloDTO.getProdotto().getId() == req.getIdProdotto())
				throw new AcademyException("Il carrello contiene già questo oggetto, usa modifica quantità");
		}
		
		//controllo se la quantità inserita è accettabile
		if(req.getQuantita() == null || req.getQuantita() < 0)
			throw new AcademyException("Quantità inserita non valida");
		
		//controllo se esiste quel prodotto
		Optional<Prodotto> prodottoRispettivo = prodR.findById(req.getIdProdotto());
		if(prodottoRispettivo.isEmpty())
			throw new AcademyException("L'id_prodotto non corrisponde a nessun prodotto nel database");

		//controllo se la quantita nel db va bene per il prodotto
		if(req.getQuantita() > prodottoRispettivo.get().getQuantita())
			throw new AcademyException("La quantita richiesta non è disponibile");
		
		//salvo nel database
		OggettoNelCarrello oggetto = new OggettoNelCarrello();
		oggetto.setQuantita(req.getQuantita());
		oggetto.setCarrello(carrelloRispettivo.get());
		oggetto.setProdotto(prodottoRispettivo.get());
		
		//devo cambiare il prezzo del carrello
		boolean menoOpiu = true;
		AggiornaPrezzoTotaleCarrelloByIdProdotto(prodottoRispettivo.get().getId(), carrelloRispettivo.get().getId(), menoOpiu);
		
		oncR.save(oggetto);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(OggettoNelCarrelloReq req) throws AcademyException {
		log.debug("update: " + req);
		
		//controllo se esiste già nel database
		Optional<OggettoNelCarrello> oggNelCarr = oncR.findById(req.getId());
		if(oggNelCarr.isEmpty())
			throw new AcademyException("Oggetto non presente nel database");
		
		//controllo se esiste il carrello
		Optional<Carrello> carrelloRispettivo = carrR.findById(req.getIdCarrello());
		if(carrelloRispettivo.isEmpty())
			throw new AcademyException("Non esiste il carrello da te inserito");		
		
		//controllo se la quantità inserita è accettabile
		if(req.getQuantita() == null || req.getQuantita() < 0)
			throw new AcademyException("Quantità inserita non valida");		
		
		//controllo se esiste quel prodotto
		Optional<Prodotto> prodottoRispettivo = prodR.findById(req.getIdProdotto());
		if(prodottoRispettivo.isEmpty())
			throw new AcademyException("L'id_prodotto non corrisponde a nessun prodotto nel database");
		//controllo se la quantita nel db va bene per il prodotto
		if(req.getQuantita() > prodottoRispettivo.get().getQuantita())
			throw new AcademyException("La quantita richiesta non è disponibile");		
		
		//l'unica cosa che puo' variare è la quantità
		//salvo nel database
		OggettoNelCarrello oggetto = new OggettoNelCarrello();
		oggetto.setQuantita(req.getQuantita());
		oggetto.setCarrello(carrelloRispettivo.get());
		oggetto.setProdotto(prodottoRispettivo.get());
		
		//devo cambiare il prezzo del carrello
		boolean menoOpiu;
		if(oggNelCarr.get().getQuantita() > req.getQuantita()) {
			menoOpiu = false;
		}else {
			menoOpiu = true;
		}
		AggiornaPrezzoTotaleCarrelloByIdProdotto(prodottoRispettivo.get().getId(), carrelloRispettivo.get().getId(), menoOpiu);
		
		oncR.save(oggetto);		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(OggettoNelCarrelloReq req) throws AcademyException {
		log.debug("delete: " + req);
		
		//controllo se esiste già nel database
		Optional<OggettoNelCarrello> oggNelCarr = oncR.findById(req.getId());
		if(oggNelCarr.isEmpty())
			throw new AcademyException("Oggetto non presente nel database");		
		
		//devo cambiare il prezzo del carrello
		boolean menoOpiu = false;
		AggiornaPrezzoTotaleCarrelloByIdProdotto(req.getIdProdotto(), req.getIdCarrello(), menoOpiu);
		
		//elimino dal database
		oncR.delete(oggNelCarr.get());		
	}
	
	////////////////////////////////

	@Override
	public List<OggettoNelCarrelloDTO> findAll() {
		log.debug("findAll oggettoNelCarrello");
		List<OggettoNelCarrello> oggetti = oncR.findAll();
		
		return oggetti.stream()
				.map(ogg -> OggettoNelCarrelloDTO.builder()
						.id(ogg.getId())
						.quantita(ogg.getQuantita())
						.carrello(buildCarrelloDTO(ogg.getCarrello()))
						.prodotto(buildProdottoDTO(ogg.getProdotto()))
						.build()).collect(Collectors.toList());	
	}

	@Override
	public OggettoNelCarrelloDTO getById(Integer id) throws AcademyException {
		log.debug("findById OggettoNelCarrello");
		
		//controllo se esiste l'oggetto
		Optional<OggettoNelCarrello> oggetto = oncR.findById(id);
		if(oggetto.isEmpty())
			throw new AcademyException("Oggetto del sium non presente nel database");
		
		return OggettoNelCarrelloDTO.builder()
				.id(oggetto.get().getId())
				.quantita(oggetto.get().getQuantita())
				.carrello(buildCarrelloDTO(oggetto.get().getCarrello()))
				.prodotto(buildProdottoDTO(oggetto.get().getProdotto()))
				.build();
	}

	@Override
	public List<OggettoNelCarrelloDTO> getByIdCarrello(Integer id) throws AcademyException {
		log.debug("findByIdCarrello OggettoNelCarrello");
		List<Optional<OggettoNelCarrello>> oggettiO = oncR.findByIdCarrello(id);
		
		//inserisco i dati dentro una lista normale
		List<OggettoNelCarrello> oggetti = new ArrayList<OggettoNelCarrello>();
		for (Optional<OggettoNelCarrello> oggetto : oggettiO) {
			oggetti.add(oggetto.get());		
		}
		
		//restituisco i dati
		return oggetti.stream()
				.map(ogg -> OggettoNelCarrelloDTO.builder()
						.id(ogg.getId())
						.quantita(ogg.getQuantita())
						.carrello(buildCarrelloDTO(ogg.getCarrello()))
						.prodotto(buildProdottoDTO(ogg.getProdotto()))
						.build()).collect(Collectors.toList());
	}
	
	////////////////////////////////

	
	//aggiorna prezzo nel carrello ++ o --
	@Transactional(rollbackFor = Exception.class)
	public void AggiornaPrezzoTotaleCarrelloByIdProdotto(Integer idProdotto, Integer idCarrello, boolean menoOpiu) throws AcademyException{
		log.debug("Start Aggiornamento prezzoTotale");
		
		//controllo se esiste il carrello
		Optional<Carrello> carrelloO = carrR.findById(idCarrello);
		if(carrelloO.isEmpty())
			throw new AcademyException("Non esiste il carrello da te inserito");
		
		//controllo se esiste quel prodotto
		Optional<Prodotto> prodottoO = prodR.findById(idProdotto);
		if(prodottoO.isEmpty())
			throw new AcademyException("L'id_prodotto non corrisponde a nessun prodotto nel database");
		
		//salviamo i dati nel database
		Carrello carrello = carrelloO.get();
		int prezzo = 0;
		if(menoOpiu) {
			prezzo = carrello.getPrezzoTotale() + prodottoO.get().getPrezzo();
		}else {
			prezzo = carrello.getPrezzoTotale() - prodottoO.get().getPrezzo();
		}
		carrello.setPrezzoTotale(prezzo);
		
		carrR.save(carrello);
	}
	
	//aggiorna numero pezzi nel carrello UNO ALLA VOLTA
	@Transactional(rollbackFor = Exception.class)
	public void AggiornaNumeroProdotti(Integer idProdotto, Integer idCarrello, int quantiti) throws AcademyException{
		log.debug("Start Aggiornamento numeroProdotti");
		
		//controllo se esiste il carrello
		Optional<Carrello> carrelloO = carrR.findById(idCarrello);
		if(carrelloO.isEmpty())
			throw new AcademyException("Non esiste il carrello da te inserito");
		
		//controllo se esiste quel prodotto
		Optional<Prodotto> prodottoO = prodR.findById(idProdotto);
		if(prodottoO.isEmpty())
			throw new AcademyException("L'id_prodotto non corrisponde a nessun prodotto nel database");
		
		//controllo se la quantita nel db va bene per il prodotto
		if(quantiti > prodottoO.get().getQuantita())
			throw new AcademyException("La quantita richiesta non è disponibile");
		
		//salviamo i dati nel database
		Carrello carrello = carrelloO.get();
		carrello.setNumeroProdotti(quantiti);
		
		carrR.save(carrello);	
	}
	
	/*
	//controllo disponibilità prodotto
	public Integer ControlloDisponibilita(Integer idProdotto) throws AcademyException{
		log.debug("Start controllo disponibilità Prodotti");
		
		//controllo se esiste quel prodotto
		Optional<Prodotto> prodottoO = prodR.findById(idProdotto);
		if(prodottoO.isEmpty())
			throw new AcademyException("L'id_prodotto non corrisponde a nessun prodotto nel database");
		
		//returno la quantità la uso dopo
		return prodottoO.get().getQuantita();
	}
	*/
	
	
	
	
	
	
	
}
