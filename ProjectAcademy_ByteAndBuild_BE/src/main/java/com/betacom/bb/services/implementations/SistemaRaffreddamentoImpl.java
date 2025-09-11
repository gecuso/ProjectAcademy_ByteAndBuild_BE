package com.betacom.bb.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.SistemaRaffreddamento;
import com.betacom.bb.repositories.ISistemaRaffreddamentoRepository;
import com.betacom.bb.requests.SistemaRaffreddamentoReq;
import com.betacom.bb.services.interfaces.ISistemaRaffreddamentoServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SistemaRaffreddamentoImpl implements ISistemaRaffreddamentoServices{

	private ISistemaRaffreddamentoRepository sysR;

	
	public SistemaRaffreddamentoImpl(ISistemaRaffreddamentoRepository sysR) {
		this.sysR = sysR;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(SistemaRaffreddamentoReq req) throws AcademyException {
		log.debug("create: " + req);
		SistemaRaffreddamento sys = new SistemaRaffreddamento();
		Optional<SistemaRaffreddamento> s = sysR.findByDescrizione(req.getDescrizione());
		if(s.isPresent())
			throw new AcademyException("Sistema di raffredamento già esistente nel database");
		
		if(req.getDescrizione().isEmpty())
			throw new AcademyException("Descrizione non presente, riprova");
		sys.setDescrizione(req.getDescrizione());
		
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente, riprova");
		sys.setConsumo(req.getConsumo());
		
		if(req.getProdotto().getId() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		sys.setProdotto(req.getProdotto());
		
		sysR.save(sys);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(SistemaRaffreddamentoReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<SistemaRaffreddamento> s = sysR.findById(req.getId());
		
		if(s.isEmpty())
			throw new AcademyException("Sistema di raffredamento non esistente");
		SistemaRaffreddamento sys = s.get();
		
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente, riprova");
		sys.setConsumo(req.getConsumo());
		
		sysR.save(sys);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(SistemaRaffreddamentoReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<SistemaRaffreddamento> s = sysR.findById(req.getId());
		
		if(s.isEmpty())
			throw new AcademyException("Alimentazione non esistente");
	
		if(!s.get().getPc().isEmpty())
			throw new AcademyException("Alimentazione contenutaa in un pc, non eliminabile");
		
		sysR.delete(s.get());
		
	}
	
}
