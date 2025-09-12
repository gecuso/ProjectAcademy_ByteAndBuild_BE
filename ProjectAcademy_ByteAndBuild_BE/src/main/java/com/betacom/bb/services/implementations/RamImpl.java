package com.betacom.bb.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Ram;
import com.betacom.bb.repositories.IRamRepository;
import com.betacom.bb.requests.RamReq;
import com.betacom.bb.services.interfaces.IRamServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RamImpl implements IRamServices{

	private IRamRepository ramR;

	public RamImpl(IRamRepository ramR) {
		this.ramR = ramR;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(RamReq req) throws AcademyException {
		log.debug("create: " + req);
		Ram ram = new Ram();
		Optional<Ram> r = ramR.findByDescrizione(req.getDescrizione());
		if(r.isPresent())
			throw new AcademyException("Ram già esistente nel database");
		
		if(req.getDescrizione().isEmpty())
			throw new AcademyException("Descrizione non presente, riprova");
		ram.setDescrizione(req.getDescrizione());
		
		if(req.getConsumo() == null)
			throw new AcademyException("Consumo nullo");
		ram.setConsumo(req.getConsumo());
		
		if(req.getProdotto().getId() == null)
			throw new AcademyException("Prodotto nullo");
		ram.setProdotto(req.getProdotto());
		
		ramR.save(ram);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(RamReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<Ram> r = ramR.findById(req.getId());
		
		if(r.isEmpty())
			throw new AcademyException("Ram non esistente");
		Ram ram = new Ram();
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente, riprova");
		ram.setConsumo(req.getConsumo());
		
		ramR.save(ram);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(RamReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<Ram> r = ramR.findById(req.getId());
		
		if(r.isEmpty())
			throw new AcademyException("Cpu non esistente");
		
		if(!r.get().getPc().isEmpty())
			throw new AcademyException("Cpu contenuta in un pc, non contenibile");
		
		ramR.delete(r.get());
		
	}
}
