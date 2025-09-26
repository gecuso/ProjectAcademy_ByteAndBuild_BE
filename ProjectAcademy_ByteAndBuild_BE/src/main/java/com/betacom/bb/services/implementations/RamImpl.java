package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.RamDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.models.Ram;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.repositories.IRamRepository;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.RamReq;
import com.betacom.bb.services.interfaces.IProdottoServices;
import com.betacom.bb.services.interfaces.IRamServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RamImpl extends Utilities implements IRamServices{

	private IRamRepository ramR;
	private IProdottoRepository prodR;
	private IProdottoServices prodS;

	public RamImpl(IRamRepository ramR, IProdottoRepository prodR, IProdottoServices prodS) {
		super();
		this.ramR = ramR;
		this.prodR = prodR;
		this.prodS = prodS;
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
		
		if(req.getIdProdotto() == null)
			throw new AcademyException("Id del prodotto non inserito, riprovare");
		Optional<Prodotto> p = prodR.findById(req.getIdProdotto());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non presente nel database");
		ram.setProdotto(p.get());
		
		ramR.save(ram);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createRamProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		Integer idprod = prodS.create(req.getProdReq());
		
		req.getRamReq().setDescrizione(req.getProdReq().getDescrizione());
		req.getRamReq().setIdProdotto(idprod);
		
		create(req.getRamReq());

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
			throw new AcademyException("Ram non esistente");
		
		if(!r.get().getPc().isEmpty())
			throw new AcademyException("Ram contenuta in un pc, non contenibile");
		
		ramR.delete(r.get());
		
	}
	
	@Override
	public RamDTO getById(Integer id) throws AcademyException {
		log.debug("getRam: " + id);
		Optional<Ram> ram = ramR.findById(id);
		
		if(ram.isEmpty())
			throw new AcademyException("Ram non esistente");
		Ram r = ram.get();
		
		return RamDTO.builder()
				.id(r.getId())
				.descrizione(r.getDescrizione())
				.consumo(r.getConsumo())
				.prodotto(buildProdottoDTO(r.getProdotto()))
				.build();
	}
	
	@Override
	public List<RamDTO> listAll() {
		log.debug("lisAll di Ram: ");
		List<Ram> lR = ramR.findAll();
		
		return  lR.stream()
				.map(r ->RamDTO.builder()
						.id(r.getId())
						.descrizione(r.getDescrizione())
						.consumo(r.getConsumo())
						.prodotto(buildProdottoDTO(r.getProdotto()))
						.build())
				.collect(Collectors.toList());
	}
}
