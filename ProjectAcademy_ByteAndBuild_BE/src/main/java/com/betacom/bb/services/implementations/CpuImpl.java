package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.CpuDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Cpu;
import com.betacom.bb.repositories.ICpuRepository;
import com.betacom.bb.requests.CpuReq;
import com.betacom.bb.services.interfaces.ICpuServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CpuImpl extends Utilities implements ICpuServices{

	private ICpuRepository cpuR;

	public CpuImpl(ICpuRepository cpuR) {
		this.cpuR = cpuR;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(CpuReq req) throws AcademyException {
		log.debug("create: " + req);
		Cpu cpu = new Cpu();
		Optional<Cpu> c = cpuR.findByDescrizione(req.getDescrizione());
		if(c.isPresent())
			throw new AcademyException("Cpu già esistente nel database");
		
		if(req.getDescrizione().isEmpty())
			throw new AcademyException("Descrizione non presente, riprova");
		cpu.setDescrizione(req.getDescrizione());
		
		if(req.getCompatibilita().isEmpty())
			throw new AcademyException("Compatibilità nulla");
		cpu.setCompatibilita(req.getCompatibilita());
		
		if(req.getConsumo() == null)
			throw new AcademyException("Consumo nullo");
		cpu.setConsumo(req.getConsumo());
		
		if(req.getProdotto().getId() == null)
			throw new AcademyException("Prodotto nullo");
		cpu.setProdotto(req.getProdotto());
		
		cpuR.save(cpu);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(CpuReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<Cpu> c = cpuR.findById(req.getId());
		
		if(c.isEmpty())
			throw new AcademyException("Cpu non esistente");
		Cpu cpu = c.get();
		
		if(req.getCompatibilita().isEmpty())
			throw new AcademyException("Compatibilità non presente, riprova");
		cpu.setCompatibilita(req.getCompatibilita());
		
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente, riprova");
		cpu.setConsumo(req.getConsumo());
		
		cpuR.save(cpu);
			
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(CpuReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<Cpu> c = cpuR.findById(req.getId());
		
		if(c.isEmpty())
			throw new AcademyException("Cpu nonn esistente");
		
		if(!c.get().getPc().isEmpty())
			throw new AcademyException("Cpu contenuta in un pc, non eliminabile");
		
		cpuR.delete(c.get());
	}
	
	@Override
	public CpuDTO getById(Integer id) throws AcademyException {
		log.debug("getCpu: " + id);
		Optional<Cpu> cpu = cpuR.findById(id);
		

		if(cpu.isEmpty())
			throw new AcademyException("Cpu nonn esistente");
		Cpu c = cpu.get();
		
		return CpuDTO.builder()
				.id(c.getId())
				.descrizione(c.getDescrizione())
				.compatibilita(c.getCompatibilita())
				.consumo(c.getConsumo())
				.prodotto(buildProdottoDTO(c.getProdotto()))
				.build();
	}
	
	@Override
	public List<CpuDTO> listAll() {
		log.debug("lisAll di Cpu: ");
		List<Cpu> lC = cpuR.findAll();
		
		return lC.stream()
				.map(c -> CpuDTO.builder()
						.id(c.getId())
						.descrizione(c.getDescrizione())
						.compatibilita(c.getCompatibilita())
						.consumo(c.getConsumo())
						.prodotto(buildProdottoDTO(c.getProdotto()))
						.build())
				.collect(Collectors.toList());
	}
}
