package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.SistemaRaffreddamentoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.models.SistemaRaffreddamento;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.repositories.ISistemaRaffreddamentoRepository;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.SistemaRaffreddamentoReq;
import com.betacom.bb.services.interfaces.IProdottoServices;
import com.betacom.bb.services.interfaces.ISistemaRaffreddamentoServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SistemaRaffreddamentoImpl extends Utilities implements ISistemaRaffreddamentoServices{

	private ISistemaRaffreddamentoRepository sysR;
	private IProdottoRepository prodR;
	private IProdottoServices prodS;
	
	public SistemaRaffreddamentoImpl(ISistemaRaffreddamentoRepository sysR, IProdottoRepository prodR,
			IProdottoServices prodS) {
		super();
		this.sysR = sysR;
		this.prodR = prodR;
		this.prodS = prodS;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(SistemaRaffreddamentoReq req) throws AcademyException {
		log.debug("create: " + req);
		SistemaRaffreddamento sys = new SistemaRaffreddamento();
		Optional<SistemaRaffreddamento> s = sysR.findByDescrizione(req.getDescrizione());
		if(s.isPresent())
			throw new AcademyException("Sistema di raffredamento già esistente nel database");
		
		if(req.getDescrizione() == null)
			throw new AcademyException("Descrizione non presente, riprova");
		sys.setDescrizione(req.getDescrizione());
		
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente, riprova");
		sys.setConsumo(req.getConsumo());
		
		if(req.getIdProdotto() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		Optional<Prodotto> p = prodR.findById(req.getIdProdotto());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non presente nel database");
		sys.setProdotto(p.get());
		
		sysR.save(sys);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createSisRafProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		Integer idprod = prodS.create(req.getProdReq());
		
		req.getSisRafReq().setDescrizione(req.getProdReq().getDescrizione());
		req.getSisRafReq().setIdProdotto(idprod);
		
		create(req.getSisRafReq());
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateSisRafProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		prodS.update(req.getProdReq());
		
		req.getSisRafReq().setDescrizione(req.getProdReq().getDescrizione());
		
		update(req.getSisRafReq());
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteSisRafProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		delete(req.getSisRafReq());
		prodS.delete(req.getProdReq().getId());
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
			throw new AcademyException("Sistema di raffredamento non esistente");
	
		if(!s.get().getPc().isEmpty())
			throw new AcademyException("Sistema di raffredamento contenutaa in un pc, non eliminabile");
		
		sysR.delete(s.get());
		
	}
	
	@Override
	public SistemaRaffreddamentoDTO getById(Integer id) throws AcademyException {
		log.debug("getAlimentazione: " + id);
		Optional<SistemaRaffreddamento> sys = sysR.findById(id);
		
		if(sys.isEmpty())
			throw new AcademyException("Sistema di raffredamento non esistente");
		SistemaRaffreddamento s = sys.get();

		return SistemaRaffreddamentoDTO.builder()
				.id(s.getId())
				.descrizione(s.getDescrizione())
				.consumo(s.getConsumo())
				.prodotto(buildProdottoDTO(s.getProdotto()))
				.build();
	}
	
	@Override
	public List<SistemaRaffreddamentoDTO> listAll() {
		log.debug("lisAll di Sistema di raffredamento: ");
		List<SistemaRaffreddamento> lS = sysR.findAll();
		
		return lS.stream()
				.map(s -> SistemaRaffreddamentoDTO.builder()
						.id(s.getId())
						.descrizione(s.getDescrizione())
						.consumo(s.getConsumo())
						.prodotto(buildProdottoDTO(s.getProdotto()))
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public SistemaRaffreddamentoDTO findByIdProd(Integer idProd) throws AcademyException {
		SistemaRaffreddamento alim = sysR.findByIdProd(idProd);
		return buildSistemaRaffreddamentoDTO(alim);
	}
}
