package com.betacom.bb.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Case;
import com.betacom.bb.repositories.ICaseRepository;
import com.betacom.bb.requests.CaseReq;
import com.betacom.bb.services.interfaces.ICaseServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CaseImpl implements ICaseServices{

	private ICaseRepository caseR;

	public CaseImpl(ICaseRepository caseR) {
		this.caseR = caseR;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(CaseReq req) throws AcademyException {
		log.debug("create: " + req);
		Case casee = new Case();
		Optional<Case> c = caseR.findByDescrizione(req.getDescrizione());
		if(c.isPresent())
			throw new AcademyException("Case già esistente nel database");
		
		if(req.getDescrizione().isEmpty())
			throw new AcademyException("Descrizione non presente, riprova");
		casee.setDescrizione(req.getDescrizione());
		
		if(req.getDimensioni().isEmpty())
			throw new AcademyException("Dimensione non presente, riprova");
		casee.setDimensioni(req.getDimensioni());
		
		if(req.getFormato().getId() == null)
			throw new AcademyException("Formato non presebte, ripova");
		casee.setFormato(req.getFormato());
		
		if(req.getProdotto().getId() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		casee.setProdotto(req.getProdotto());
		
	}
	

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(CaseReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<Case> c = caseR.findById(req.getId());
		
		if(c.isEmpty())
			throw new AcademyException("SchedaMadre non esistente");
		
		if(!c.get().getPc().isEmpty())
			throw new AcademyException("SchedaMadre contenutaa in un pc, non eliminabile");
		
		caseR.delete(c.get());
		
	}
}
