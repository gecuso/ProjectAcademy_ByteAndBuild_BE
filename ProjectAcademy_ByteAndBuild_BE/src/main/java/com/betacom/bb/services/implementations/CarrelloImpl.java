package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.CarrelloDTO;
import com.betacom.bb.dto.UtenteDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Carrello;
import com.betacom.bb.models.OggettoNelCarrello;
import com.betacom.bb.models.Utente;
import com.betacom.bb.repositories.ICarrelloRepository;
import com.betacom.bb.repositories.IOggettoNelCarrelloRepository;
import com.betacom.bb.repositories.IUtenteRepository;
import com.betacom.bb.requests.CarrelloReq;
import com.betacom.bb.services.interfaces.ICarrelloService;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CarrelloImpl extends Utilities implements ICarrelloService{

	private ICarrelloRepository carrR;
	private IUtenteRepository utenR;
	private IOggettoNelCarrelloRepository oncR;
	
	public CarrelloImpl(ICarrelloRepository carrR, IUtenteRepository utenR, IOggettoNelCarrelloRepository oncR) {
		this.carrR = carrR;
		this.utenR = utenR;
		this.oncR = oncR;
	}
	
	////////////////////////////////	

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(CarrelloReq req) throws AcademyException {
		log.debug("create: " + req);
		
		//controllo se esiste già nel database
		Optional<Carrello> car = carrR.findById(req.getId());
		if(!car.isEmpty())
			throw new AcademyException("Carrello già presente nel database");
		
		//controllo se esiste l'utente
		Optional<Utente> ute = utenR.findById(req.getIdUtente());
		if(ute.isEmpty())
			throw new AcademyException("Utente non presente nel database");		
		//controllo se quell'utente ha già un carrello	
		Optional<Carrello> car2 = carrR.findByIdUtente(req.getIdUtente());
		if(!car2.isEmpty())
			throw new AcademyException("Questo utente ha già un carrello");
		
		//il controllo del resto dei dati non è necessario
		//perché nOggetti e pTotale, nel create li segno a 0
		
		//salvo nel database
		Carrello carrello = new Carrello();
		carrello.setUtente(ute.get());
		carrello.setNumeroProdotti(0);
		carrello.setPrezzoTotale(0);
		
		carrR.save(carrello);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(CarrelloReq req) throws AcademyException {
		log.debug("update: " + req);
		
		//controllo se esiste nel database
		Optional<Carrello> car = carrR.findById(req.getId());
		if(car.isEmpty())
			throw new AcademyException("Carrello non presente nel database");		
		
		//modifico i dati
		//non posso modificare ne id che id_utente
		if(req.getNumeroProdotti() == null || req.getNumeroProdotti() < 0)		
			throw new AcademyException("Numero di prodotti non valido");
		if(req.getPrezzoTotale() == null || req.getPrezzoTotale() < 0)
			throw new AcademyException("Prezzo totale non valido");
		
		//salvo nel database
		Carrello carrello = car.get();
		carrello.setNumeroProdotti(req.getNumeroProdotti());
		carrello.setPrezzoTotale(req.getPrezzoTotale());
		
		carrR.save(carrello);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(CarrelloReq req) throws AcademyException {
		log.debug("delete: " + req);
		
		//controllo se esiste nel database
		Optional<Carrello> car = carrR.findById(req.getId());
		if(car.isEmpty())
			throw new AcademyException("Carrello non presente nel database");
		
		//devo controllare se non ci sono oggetti all'interno
		//se ce ne sono non posso eliminare
		List<Optional<OggettoNelCarrello>> oggettiInterni = oncR.findByIdCarrello(req.getId());
		if(oggettiInterni.isEmpty()) {
			//elimino
			carrR.delete(car.get());
		}else {
			throw new AcademyException("Ci sono degli oggetti all'interno del carrello, elimina prima quelli...");
		}
	}

	////////////////////////////////
	
	@Override
	public List<CarrelloDTO> findAll() {
		log.debug("findAll carrello");	
		List<Carrello> carrelli = carrR.findAll();
		
		return carrelli.stream()
				.map(car -> CarrelloDTO.builder()
						.id(car.getId())
						.numeroProdotti(car.getNumeroProdotti())
						.prezzoTotale(car.getPrezzoTotale())
						.utente(UtenteDTO.builder()
								.id(car.getUtente().getId())
								.userName(car.getUtente().getUserName())
								.pwd(car.getUtente().getPwd())
								.email(car.getUtente().getEmail())
								.indirizzo(car.getUtente().getIndirizzo())
								.telefono(car.getUtente().getTelefono())
								.role(car.getUtente().getRole().toString())
								.build())
						.build()).collect(Collectors.toList());
	}

	@Override
	public CarrelloDTO getById(Integer id) throws AcademyException {
		log.debug("findById carrello");
		
		//controllo se esiste il carrello
		Optional<Carrello> carrello = carrR.findById(id);
		if(carrello.isEmpty())
			throw new AcademyException("Carrello non presente nel database");
		
		//lo restituisco
		return CarrelloDTO.builder()
				.id(carrello.get().getId())
				.numeroProdotti(carrello.get().getNumeroProdotti())
				.prezzoTotale(carrello.get().getPrezzoTotale())
				.utente(UtenteDTO.builder()
						.id(carrello.get().getUtente().getId())
						.userName(carrello.get().getUtente().getUserName())
						.pwd(carrello.get().getUtente().getPwd())
						.email(carrello.get().getUtente().getEmail())
						.indirizzo(carrello.get().getUtente().getIndirizzo())
						.telefono(carrello.get().getUtente().getTelefono())
						.role(carrello.get().getUtente().getRole().toString())
						.build())
				.build();	
	}

	@Override
	public CarrelloDTO getByIdUtente(Integer id) throws AcademyException {
		log.debug("findByIdUtente carrello");
		
		//controllo se esiste il carrello
		Optional<Carrello> carrello = carrR.findByIdUtente(id);
		if(carrello.isEmpty())
			throw new AcademyException("Carrello non presente nel database");
		
		//lo restituisco
		return CarrelloDTO.builder()
				.id(carrello.get().getId())
				.numeroProdotti(carrello.get().getNumeroProdotti())
				.prezzoTotale(carrello.get().getPrezzoTotale())
				.utente(UtenteDTO.builder()
						.id(carrello.get().getUtente().getId())
						.userName(carrello.get().getUtente().getUserName())
						.pwd(carrello.get().getUtente().getPwd())
						.email(carrello.get().getUtente().getEmail())
						.indirizzo(carrello.get().getUtente().getIndirizzo())
						.telefono(carrello.get().getUtente().getTelefono())
						.role(carrello.get().getUtente().getRole().toString())
						.build())
				.build();
	}
	
	////////////////////////////////

	
	
	
	
	
	
	
	
	
	
	
	
}
