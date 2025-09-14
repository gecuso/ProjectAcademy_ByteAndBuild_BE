package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.FormatoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Formato;
import com.betacom.bb.repositories.IFormatoRepository;
import com.betacom.bb.requests.FormatoReq;
import com.betacom.bb.services.interfaces.IFormatoServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class FormatoImpl implements IFormatoServices{

	private IFormatoRepository fR;

	public FormatoImpl(IFormatoRepository fR) {
		this.fR = fR;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(FormatoReq req) throws AcademyException {
		log.debug("create: " + req);
		Formato form = new Formato();
		Optional<Formato> f = fR.findByDescrizione(req.getDescrizione());
		if(f.isPresent())
			throw new AcademyException("Formato già esistente nel database");
		
		if(req.getDescrizione()== null)
			throw new AcademyException("Descrizione non presente, riprova");
		form.setDescrizione(req.getDescrizione());
		
		fR.save(form);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(FormatoReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<Formato> f = fR.findById(req.getId());
		
		if(f.isEmpty())
			throw new AcademyException("Formato non esistente");
	
		if(!f.get().getSchedaMadre().isEmpty())
			throw new AcademyException("Di questo Fromato ci sono ancora delle schede madri, non eliminabile");
		
		if(!f.get().getCasee().isEmpty())
			throw new AcademyException("Di questo Fromato ci sono ancora dei case, non eliminabile");
		
		
		fR.delete(f.get());
	}
	
	
	@Override
	public FormatoDTO getById(Integer id) throws AcademyException {
		log.debug("getFormato: " + id);
		Optional<Formato> form = fR.findById(id);
		
		if(form.isEmpty())
			throw new AcademyException("Alimentatore non esistente");
		Formato f = form.get();

		return FormatoDTO.builder()
				.id(f.getId())
				.descrizione(f.getDescrizione())
				.build();
	}

	@Override
	public List<FormatoDTO> listAll() {
		log.debug("lisAll di Alimentazione: ");
		List<Formato> lF = fR.findAll();
		
		return lF.stream()
				.map(f -> FormatoDTO.builder()
						.id(f.getId())
						.descrizione(f.getDescrizione())
						.build())
				.collect(Collectors.toList());
	}
}
