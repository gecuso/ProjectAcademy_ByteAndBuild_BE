package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.CaseDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Case;
import com.betacom.bb.models.Formato;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.ICaseRepository;
import com.betacom.bb.repositories.IFormatoRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.CaseReq;
import com.betacom.bb.services.interfaces.ICaseServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CaseImpl extends Utilities implements ICaseServices{

	private ICaseRepository caseR;
	private IFormatoRepository formR;
	private IProdottoRepository prodR;
	
	
	public CaseImpl(ICaseRepository caseR, IFormatoRepository formR, IProdottoRepository prodR) {
		this.caseR = caseR;
		this.formR = formR;
		this.prodR = prodR;
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
		
		if(req.getIdFormato() == null)
			throw new AcademyException("Formato non presebte, ripova");
		Optional<Formato> f = formR.findById(req.getIdFormato());
		if(f.isEmpty())
			throw new AcademyException("Formato non presente nel database");
		casee.setFormato(f.get());
		
		if(req.getIdProdotto() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		Optional<Prodotto> p = prodR.findById(req.getIdProdotto());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non presente nel database");
		casee.setProdotto(p.get());
		
	}
	

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(CaseReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<Case> c = caseR.findById(req.getId());
		
		if(c.isEmpty())
			throw new AcademyException("Case non esistente");
		
		if(!c.get().getPc().isEmpty())
			throw new AcademyException("Case contenutaa in un pc, non eliminabile");
		
		caseR.delete(c.get());
		
	}
	
	@Override
	public CaseDTO getById(Integer id) throws AcademyException {
		log.debug("getCase: " + id);
		Optional<Case> cs = caseR.findById(id);
		
		if(cs.isEmpty())
			throw new AcademyException("Case non esistente");
		Case c = cs.get();
		
		return CaseDTO.builder()
				.id(c.getId())
				.descrizione(c.getDescrizione())
				.dimensioni(c.getDimensioni())
				.prodotto(buildProdottoDTO(c.getProdotto()))
				.formato(buildFormatoDTO(c.getFormato()))
				.build();
	}
	
	@Override
	public List<CaseDTO> listAll() {
		log.debug("lisAll di Case: ");
		List<Case> lC = caseR.findAll();
		
		return lC.stream()
				.map(c -> CaseDTO.builder()
				.id(c.getId())
				.descrizione(c.getDescrizione())
				.dimensioni(c.getDimensioni())
				.prodotto(buildProdottoDTO(c.getProdotto()))
				.formato(buildFormatoDTO(c.getFormato()))
				.build())
				.collect(Collectors.toList());
	}
}
