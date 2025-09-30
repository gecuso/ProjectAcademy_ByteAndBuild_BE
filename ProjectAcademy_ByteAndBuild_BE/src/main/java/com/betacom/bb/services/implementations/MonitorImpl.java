package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.MonitorDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Monitor;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.IMonitorRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.MonitorReq;
import com.betacom.bb.services.interfaces.IMonitorService;
import com.betacom.bb.services.interfaces.IProdottoServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MonitorImpl extends Utilities implements IMonitorService{

	private IMonitorRepository monR;
	private IProdottoRepository prodR;
	private IProdottoServices prodS;
	
	////////////////////////////////

	public MonitorImpl(IMonitorRepository monR, IProdottoRepository prodR, IProdottoServices prodS) {
		super();
		this.monR = monR;
		this.prodR = prodR;
		this.prodS = prodS;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(MonitorReq req) throws AcademyException {
		log.debug("create: " + req);
		Optional<Monitor> mon = monR.findByDescrizione(req.getDescrizione());
		if(mon.isPresent())
			throw new AcademyException("Monitor già presente nel database");
		
		//controllo dei dati
		Monitor monitor = new Monitor();
		if(req.getDescrizione()==null)
			throw new AcademyException("Descrizione non presente, riprovare");
		monitor.setDescrizione(req.getDescrizione());
		if(req.getRisoluzione()==null)
			throw new AcademyException("Risoluzione non presente, riprovare");
		monitor.setRisoluzione(req.getRisoluzione());
		if(req.getLatenza()==null)
			throw new AcademyException("Latenza non presente, riprovare");
		monitor.setLatenza(req.getLatenza());
		if(req.getFrequenza()==null)
			throw new AcademyException("Frequenza non presente, riprovare");
		monitor.setFrequenza(req.getFrequenza());
		
		if(req.getIdProdotto() == null)
			throw new AcademyException("Id del prodotto non inserito, riprovare");
		Optional<Prodotto> p = prodR.findById(req.getIdProdotto());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non presente nel database");
		monitor.setProdotto(p.get());
		
		//salvo nel database
		monR.save(monitor);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createMonitorProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		Integer idprod = prodS.create(req.getProdReq());
		
		req.getMonitorReq().setDescrizione(req.getProdReq().getDescrizione());
		req.getMonitorReq().setIdProdotto(idprod);
		
		create(req.getMonitorReq());

	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateMonitorProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		prodS.update(req.getProdReq());
		
		req.getMonitorReq().setDescrizione(req.getProdReq().getDescrizione());		
		update(req.getMonitorReq());
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteMonitorProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		delete(req.getMonitorReq());
		prodS.delete(req.getProdReq().getId());
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
		monitor.setDescrizione(mon.get().getDescrizione());
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
						.descrizione(mon.getDescrizione())
						.risoluzione(mon.getRisoluzione())
						.latenza(mon.getLatenza())
						.frequenza(mon.getFrequenza())
						.prodotto(buildProdottoDTO(mon.getProdotto()))
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
				.descrizione(mon.getDescrizione())
				.risoluzione(mon.getRisoluzione())
				.latenza(mon.getLatenza())
				.frequenza(mon.getFrequenza())
				.prodotto(buildProdottoDTO(mon.getProdotto()))
				.build();
	}

	@Override
	public MonitorDTO findByIdProd(Integer idProd) throws AcademyException {
		Monitor mon = monR.findByIdProd(idProd);
		return MonitorDTO.builder()
				.id(mon.getId())
				.descrizione(mon.getDescrizione())
				.risoluzione(mon.getRisoluzione())
				.latenza(mon.getLatenza())
				.frequenza(mon.getFrequenza())
				.prodotto(buildProdottoDTO(mon.getProdotto()))
				.build();
	}	
	
}
