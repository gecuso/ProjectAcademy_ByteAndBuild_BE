package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.MonitorDTO;
import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Monitor;
import com.betacom.bb.repositories.IMonitorRepository;
import com.betacom.bb.requests.MonitorReq;
import com.betacom.bb.services.interfaces.IMonitorService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MonitorImpl implements IMonitorService{

	private IMonitorRepository monR;

	public MonitorImpl(IMonitorRepository monR) {
		this.monR = monR;
	}
	
	////////////////////////////////

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(MonitorReq req) throws AcademyException {
		log.debug("create: " + req);
//		Optional<Monitor> mon = monR.findByDescrizione(req.getDescrizione());
//		if(mon.isPresent())
//			throw new AcademyException("Monitor già presente nel database");
//		
		//controllo dei dati
		Monitor monitor = new Monitor();
//		if(req.getDescrizione()==null)
//			throw new AcademyException("Descrizione non presente, riprovare");
//		monitor.setDescrizione(req.getDescrizione());
		if(req.getRisoluzione()==null)
			throw new AcademyException("Risoluzione non presente, riprovare");
		monitor.setRisoluzione(req.getRisoluzione());
		if(req.getLatenza()==null)
			throw new AcademyException("Latenza non presente, riprovare");
		monitor.setLatenza(req.getLatenza());
		if(req.getFrequenza()==null)
			throw new AcademyException("Frequenza non presente, riprovare");
		monitor.setFrequenza(req.getFrequenza());
		
		if(req.getProdotto().getId() == null)
			throw new AcademyException("Id del prodotto non inserito, riprovare");
		monitor.setProdotto(req.getProdotto());
		
		//salvo nel database
		monR.save(monitor);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(MonitorReq req) throws AcademyException {
		log.debug("update: " + req);
		Optional<Monitor> mon = monR.findById(req.getId());
		if(mon.isEmpty())
			throw new AcademyException("Monitor non presente nel database");
		
		//controllo dei dati
		Monitor monitor = mon.get();
		//descrizione non può cambiare
//		monitor.setDescrizione(mon.get().getDescrizione());
		if(req.getRisoluzione()==null)
			throw new AcademyException("Risoluzione non presente, riprovare");
		monitor.setRisoluzione(req.getRisoluzione());
		if(req.getLatenza()==null)
			throw new AcademyException("Latenza non presente, riprovare");
		monitor.setLatenza(req.getLatenza());
		if(req.getFrequenza()==null)
			throw new AcademyException("Frequenza non presente, riprovare");
		monitor.setFrequenza(req.getFrequenza());
		
		//update nel database
		monR.save(monitor);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(MonitorReq req) throws AcademyException {
		log.debug("delete: " + req);
		Optional<Monitor> mon = monR.findById(req.getId());
		if(mon.isEmpty())
			throw new AcademyException("Monitor non presente nel database");
		
		//elimino nel database
		monR.delete(mon.get());
	}

	////////////////////////////////
	
	@Override
	public List<MonitorDTO> findAll() throws AcademyException {
		log.debug("findAll monitor");
		List<Monitor> listMonitor = monR.findAll();
		
		return listMonitor.stream()
				.map(mon -> MonitorDTO.builder()
						.id(mon.getId())
//						.descrizione(mon.getDescrizione())
						.risoluzione(mon.getRisoluzione())
						.latenza(mon.getLatenza())
						.frequenza(mon.getFrequenza())
						.prodotto(ProdottoDTO.builder()
								.id(mon.getProdotto().getId())
								.build())
						.build()).collect(Collectors.toList());
	}

	@Override
	public MonitorDTO getById(Integer id) throws AcademyException {
		log.debug("get Monitor by Id: " + id);
		
		//controllo se il mouse esiste
		Optional<Monitor> m = monR.findById(id);
		if(m.isEmpty())
			throw new AcademyException("Monitor non presente nel database");
		
		Monitor mon = m.get();
		return MonitorDTO.builder()
				.id(mon.getId())
//				.descrizione(mon.getDescrizione())
				.risoluzione(mon.getRisoluzione())
				.latenza(mon.getLatenza())
				.frequenza(mon.getFrequenza())
				.prodotto(ProdottoDTO.builder()
						.id(mon.getProdotto().getId())
						.build())
				.build();
	}	
	
}
