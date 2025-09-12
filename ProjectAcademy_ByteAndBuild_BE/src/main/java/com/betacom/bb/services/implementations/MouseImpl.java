package com.betacom.bb.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.MouseDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Mouse;
import com.betacom.bb.repositories.IMouseRepository;
import com.betacom.bb.requests.MouseReq;
import com.betacom.bb.services.interfaces.IMouseService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MouseImpl implements IMouseService{

	private IMouseRepository mouseR;

	public MouseImpl(IMouseRepository mouseR) {
		this.mouseR = mouseR;
	}
	
	////////////////////////////////
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(MouseReq req) throws AcademyException {
		log.debug("create: " + req);
		Optional<Mouse> mou = mouseR.findByDescrizione(req.getDescrizione());
		if(mou.isPresent())
			throw new AcademyException("Mouse già presente nel database");
		
		//controllo dei dati
		Mouse mouse = new Mouse();
		if(req.getDescrizione().isEmpty())
			throw new AcademyException("Descrizione non presente, riprovare");
		mouse.setDescrizione(req.getDescrizione());
		if(req.getCollegamento().isEmpty())
			throw new AcademyException("Collegamento non presente, riprovare");
		mouse.setCollegamento(req.getCollegamento());
		
		if(req.getProdotto().getId() == null)
			throw new AcademyException("Id del prodotto non inserito, riprovare");
		mouse.setProdotto(req.getProdotto());
		
		//salvo nel database
		mouseR.save(mouse);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(MouseReq req) throws AcademyException {
		log.debug("create: " + req);
		Optional<Mouse> mou = mouseR.findByDescrizione(req.getDescrizione());
		if(mou.isEmpty())
			throw new AcademyException("Mouse non presente nel database");
		
		//controllo dei dati
		Mouse mouse = new Mouse();
		mouse.setId(mou.get().getId());
		//descrizione non può cambiare
		mouse.setDescrizione(mou.get().getDescrizione());
		if(req.getCollegamento().isEmpty())
			throw new AcademyException("Collegamento non presente, riprovare");
		mouse.setCollegamento(req.getCollegamento());
		//id prodotto non deve cambiare
		mouse.setProdotto(mou.get().getProdotto());
		
		//update nel database
		mouseR.save(mouse);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(MouseReq req) throws AcademyException {
		log.debug("create: " + req);
		Optional<Mouse> mou = mouseR.findByDescrizione(req.getDescrizione());
		if(mou.isEmpty())
			throw new AcademyException("Mouse non presente nel database");
		
		//elimino nel database
		mouseR.delete(mou.get());	
	}

	////////////////////////////////
	
	//da finire post pull
	
//	@Override
//	public MouseDTO getById(Integer id) throws AcademyException {
//		log.debug("get Mouse by Id: " + id);
//		
//		//controllo se il mouse esiste
//		Optional<Mouse> m = mouseR.findById(id);
//		if(m.isEmpty())
//			throw new AcademyException("Mouse non presente nel database");
//		
//		Mouse mou = m.get();
//		return MouseDTO.builder()
//				.id(mou.getId())
//				.descrizione(mou.getDescrizione())
//				.collegamento(mou.getCollegamento())
//				.
//				.build();
//				
//	}
	
	
	
	
	
	
	
	
}
