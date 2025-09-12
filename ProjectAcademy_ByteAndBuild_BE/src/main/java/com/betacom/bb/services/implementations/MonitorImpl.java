package com.betacom.bb.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		Optional<Monitor> mon = monR.findByDescrizione(req.getDescrizione());
		if(mon.isPresent())
			throw new AcademyException("Monitor già presente nel database");
		
		//controllo dei dati
		Monitor monitor = new Monitor();
		if(req.getDescrizione().isEmpty())
			throw new AcademyException("Descrizione non presente, riprovare");
		monitor.setDescrizione(req.getDescrizione());
		if(req.getRisoluzione().isEmpty())
			throw new AcademyException("Risoluzione non presente, riprovare");
		monitor.setRisoluzione(req.getRisoluzione());
		if(req.getLatenza().isEmpty())
			throw new AcademyException("Latenza non presente, riprovare");
		monitor.setLatenza(req.getLatenza());
		if(req.getFrequenza().isEmpty())
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
		Monitor monitor = new Monitor();
		monitor.setId(mon.get().getId());
		//descrizione non può cambiare
		monitor.setDescrizione(mon.get().getDescrizione());
		if(req.getRisoluzione().isEmpty())
			throw new AcademyException("Risoluzione non presente, riprovare");
		monitor.setDescrizione(req.getRisoluzione());
		if(req.getLatenza().isEmpty())
			throw new AcademyException("Latenza non presente, riprovare");
		monitor.setLatenza(req.getLatenza());
		if(req.getFrequenza().isEmpty())
			throw new AcademyException("Frequenza non presente, riprovare");
		monitor.setFrequenza(req.getFrequenza());
		//id prodotto non deve cambiare
		monitor.setProdotto(mon.get().getProdotto());
		
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
	
		
	
}
