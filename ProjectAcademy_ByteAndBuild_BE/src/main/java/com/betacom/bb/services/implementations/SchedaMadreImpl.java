package com.betacom.bb.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.SchedaMadre;
import com.betacom.bb.repositories.ISchedaMadreRepository;
import com.betacom.bb.requests.SchedaMadreReq;
import com.betacom.bb.services.interfaces.ISchedaMadreServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SchedaMadreImpl implements ISchedaMadreServices{


	private ISchedaMadreRepository smR;

	public SchedaMadreImpl(ISchedaMadreRepository smR) {
		this.smR = smR;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(SchedaMadreReq req) throws AcademyException {
		log.debug("create: " + req);
		SchedaMadre smadre = new SchedaMadre();
		Optional<SchedaMadre> s = smR.findByDescrizione(req.getDescrizione());
		if(s.isPresent())
			throw new AcademyException("SchedaMadre già esistente nel database");
		
		if(req.getDescrizione().isEmpty())
			throw new AcademyException("Descrizione non presente, riprova");
		smadre.setDescrizione(req.getDescrizione());
		
		if(req.getCompatibilita().isEmpty())
			throw new AcademyException("Compatibilità non presente, riprova");
		smadre.setCompatibilita(req.getCompatibilita());
		
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente, riprova");
		smadre.setConsumo(req.getConsumo());
		
		if(req.getFormato().getId() == null)
			throw new AcademyException("Formato non presebte, ripova");
		smadre.setFormato(req.getFormato());
		
		if(req.getProdotto().getId() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		smadre.setProdotto(req.getProdotto());
		
		smR.save(smadre);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(SchedaMadreReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<SchedaMadre> s = smR.findByDescrizione(req.getDescrizione());
		
		if(s.isEmpty())
			throw new AcademyException("SchedaMadre non esistente");
		SchedaMadre smadre = s.get();
		
		if(req.getCompatibilita().isEmpty())
			throw new AcademyException("Compatibilità non presente, riprova");
		smadre.setCompatibilita(req.getCompatibilita());
		
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente, riprova");
		smadre.setConsumo(req.getConsumo());
		
		smR.save(smadre);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(SchedaMadreReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<SchedaMadre> s = smR.findById(req.getId());
		
		if(s.isEmpty())
			throw new AcademyException("SchedaMadre non esistente");
		
		if(!s.get().getPc().isEmpty())
			throw new AcademyException("SchedaMadre contenutaa in un pc, non eliminabile");
		
		smR.delete(s.get());
	}
}
