package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.SchedaMadreDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Formato;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.models.SchedaMadre;
import com.betacom.bb.repositories.ICaseRepository;
import com.betacom.bb.repositories.IFormatoRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.repositories.ISchedaMadreRepository;
import com.betacom.bb.requests.SchedaMadreReq;
import com.betacom.bb.services.interfaces.ISchedaMadreServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SchedaMadreImpl extends Utilities implements ISchedaMadreServices{


	private ISchedaMadreRepository smR;
	private IFormatoRepository formR;
	private IProdottoRepository prodR;
	
	
	public SchedaMadreImpl(ISchedaMadreRepository smR, IFormatoRepository formR, IProdottoRepository prodR) {
		this.smR = smR;
		this.formR = formR;
		this.prodR = prodR;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(SchedaMadreReq req) throws AcademyException {
		log.debug("create: " + req);
		SchedaMadre smadre = new SchedaMadre();
		Optional<SchedaMadre> s = smR.findByDescrizione(req.getDescrizione());
		if(s.isPresent())
			throw new AcademyException("SchedaMadre già esistente nel database");
		
		if(req.getDescrizione() == null)
			throw new AcademyException("Descrizione non presente, riprova");
		smadre.setDescrizione(req.getDescrizione());
		
		if(req.getCompatibilita() == null)
			throw new AcademyException("Compatibilità non presente, riprova");
		smadre.setCompatibilita(req.getCompatibilita());
		
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente, riprova");
		smadre.setConsumo(req.getConsumo());
		
		if(req.getIdFormato() == null)
			throw new AcademyException("Formato non presebte, ripova");
		Optional<Formato> f = formR.findById(req.getIdFormato());
		if(f.isEmpty())
			throw new AcademyException("Formato non presente nel database");
		smadre.setFormato(f.get());
		
		if(req.getIdProdotto() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		Optional<Prodotto> p = prodR.findById(req.getIdProdotto());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non presente nel database");
		smadre.setProdotto(p.get());
		
		smR.save(smadre);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(SchedaMadreReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<SchedaMadre> s = smR.findById(req.getId());
		
		if(s.isEmpty())
			throw new AcademyException("SchedaMadre non esistente");
		SchedaMadre smadre = s.get();
		
		if(req.getCompatibilita() == null)
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
	
	@Override
	public SchedaMadreDTO getById(Integer id) throws AcademyException {
		log.debug("getSchedaMAdre " + id);
		Optional<SchedaMadre> sm = smR.findById(id);
		
		if(sm.isEmpty())
			throw new AcademyException("SchedaMadre non esistente");
		SchedaMadre s = sm.get();
		
		return SchedaMadreDTO.builder()
				.id(s.getId())
				.descrizione(s.getDescrizione())
				.consumo(s.getConsumo())
				.prodotto(buildProdottoDTO(s.getProdotto()))
				.formato(buildFormatoDTO(s.getFormato()))
				.build();	
		}
	
	@Override
	public List<SchedaMadreDTO> listAll() {
		log.debug("lisAll di SchedaMadre: ");
		List<SchedaMadre> lS = smR.findAll();
		
		return lS.stream()
				.map(s -> SchedaMadreDTO.builder()
						.id(s.getId())
						.descrizione(s.getDescrizione())
						.consumo(s.getConsumo())
						.prodotto(buildProdottoDTO(s.getProdotto()))
						.formato(buildFormatoDTO(s.getFormato()))
						.build())
				.collect(Collectors.toList());
	}
}
