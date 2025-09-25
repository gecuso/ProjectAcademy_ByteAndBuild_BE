package com.betacom.bb.services.implementations;

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
		//controllo se ci sono abbastanza prodotti di quel tipo
		if(prodottoRispettivo.get().getQuantita() < req.getQuantita())
			throw new AcademyException("Non ci sono abbastanza prodotti di quel tipo per la richiesta");
		
		//salvo nel database
		OggettoNelCarrello oggetto = new OggettoNelCarrello();
		oggetto.setQuantita(req.getQuantita());
		oggetto.setCarrello(carrelloRispettivo.get());
		oggetto.setProdotto(prodottoRispettivo.get());
		
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
		//controllo se ci sono abbastanza prodotti di quel tipo
		if(prodottoRispettivo.get().getQuantita() < req.getQuantita())
			throw new AcademyException("Non ci sono abbastanza prodotti di quel tipo per la richiesta");		
		
		//l'unica cosa che puo' variare è la quantità
		//salvo nel database
		OggettoNelCarrello oggetto = new OggettoNelCarrello();
		oggetto.setQuantita(req.getQuantita());
		oggetto.setCarrello(carrelloRispettivo.get());
		oggetto.setProdotto(prodottoRispettivo.get());
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OggettoNelCarrelloDTO> getByIdCarrello(Integer id) throws AcademyException {
		// TODO Auto-generated method stub
		return null;
	}
	
	////////////////////////////////

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
