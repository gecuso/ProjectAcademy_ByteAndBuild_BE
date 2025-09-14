package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.AlimentazioneDTO;
import com.betacom.bb.dto.SchedaGraficaDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Alimentazione;
import com.betacom.bb.models.SchedaGrafica;
import com.betacom.bb.repositories.ISchedaGraficaRepository;
import com.betacom.bb.requests.SchedaGraficaReq;
import com.betacom.bb.services.interfaces.ISchedaGraficaServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class SchedaGraficaImpl extends Utilities implements ISchedaGraficaServices{

	private ISchedaGraficaRepository sgR;

	public SchedaGraficaImpl(ISchedaGraficaRepository sgR) {
		this.sgR = sgR;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(SchedaGraficaReq req) throws AcademyException {
		log.debug("create: " + req);
		SchedaGrafica sgrafica = new SchedaGrafica();
//		Optional<SchedaGrafica> s = sgR.findByDescrizione(req.getDescrizione());
//		if(s.isPresent())
//			throw new AcademyException("SchedaGrafica già esistente nel database");
//		
//		if(req.getDescrizione() == null)
//			throw new AcademyException("Descrizione non presente, riprova");
//		sgrafica.setDescrizione(req.getDescrizione());
		
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente, riprova");
		sgrafica.setConsumo(req.getConsumo());
		
		if(req.getProdotto().getId() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		sgrafica.setProdotto(req.getProdotto());
		
		sgR.save(sgrafica);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(SchedaGraficaReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<SchedaGrafica> s = sgR.findById(req.getId());
		
		if(s.isEmpty())
			throw new AcademyException("SchedaGrafica non esistente");
		SchedaGrafica sgrafica = s.get();
		
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente, riprova");
		sgrafica.setConsumo(req.getConsumo());
		
		sgR.save(sgrafica);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(SchedaGraficaReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<SchedaGrafica> s = sgR.findById(req.getId());
		
		if(s.isEmpty())
			throw new AcademyException("SchedaGrafica non esistente");
	
		if(!s.get().getPc().isEmpty())
			throw new AcademyException("SchedaGrafica contenutaa in un pc, non eliminabile");
		
		sgR.delete(s.get());
		
	}@Override
	public SchedaGraficaDTO getById(Integer id) throws AcademyException {
		log.debug("getSchedaGrafica " + id);
		Optional<SchedaGrafica> sg = sgR.findById(id);
		
		if(sg.isEmpty())
			throw new AcademyException("SchedaGrafica non esistente");
		SchedaGrafica s = sg.get();
		
		return SchedaGraficaDTO.builder()
				.id(s.getId())
//				.descrizione(s.getDescrizione())
				.consumo(s.getConsumo())
				.prodotto(buildProdottoDTO(s.getProdotto()))
				.build();
	}
	
	@Override
	public List<SchedaGraficaDTO> listAll() {
		log.debug("lisAll di SchedaGrafica: ");
		List<SchedaGrafica> lS = sgR.findAll();
		
		return lS.stream()
				.map(s -> SchedaGraficaDTO.builder()
						.id(s.getId())
//						.descrizione(s.getDescrizione())
						.consumo(s.getConsumo())
						.prodotto(buildProdottoDTO(s.getProdotto()))
						.build())
				.collect(Collectors.toList());
	}
	
	
}
