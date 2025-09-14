package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.FormatoDTO;
import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Alimentazione;
import com.betacom.bb.models.Formato;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.IAlimentazioneRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.AlimentazioneReq;
import com.betacom.bb.requests.CaseReq;
import com.betacom.bb.requests.CpuReq;
import com.betacom.bb.requests.FormatoReq;
import com.betacom.bb.requests.LaptopReq;
import com.betacom.bb.requests.MemoriaReq;
import com.betacom.bb.requests.MonitorReq;
import com.betacom.bb.requests.MouseReq;
import com.betacom.bb.requests.PcReq;
import com.betacom.bb.requests.ProdottoReq;
import com.betacom.bb.requests.RamReq;
import com.betacom.bb.requests.SchedaGraficaReq;
import com.betacom.bb.requests.SchedaMadreReq;
import com.betacom.bb.requests.SistemaRaffreddamentoReq;
import com.betacom.bb.requests.TastieraReq;
import com.betacom.bb.services.interfaces.IAlimentazioneServices;
import com.betacom.bb.services.interfaces.ICaseServices;
import com.betacom.bb.services.interfaces.ICpuServices;
import com.betacom.bb.services.interfaces.ILaptopService;
import com.betacom.bb.services.interfaces.IMemoriaService;
import com.betacom.bb.services.interfaces.IMonitorService;
import com.betacom.bb.services.interfaces.IMouseService;
import com.betacom.bb.services.interfaces.IPcService;
import com.betacom.bb.services.interfaces.IProdottoServices;
import com.betacom.bb.services.interfaces.IRamServices;
import com.betacom.bb.services.interfaces.ISchedaGraficaServices;
import com.betacom.bb.services.interfaces.ISchedaMadreServices;
import com.betacom.bb.services.interfaces.ISistemaRaffreddamentoServices;
import com.betacom.bb.services.interfaces.ITastieraService;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProdottoImpl extends Utilities implements IProdottoServices{

	private IProdottoRepository prodR;
	private IAlimentazioneServices alimS;
	private ICaseServices csS;
	private ICpuServices cpuS;
	private ILaptopService lapS;
	private IMemoriaService memS;
	private IMonitorService monS;
	private IMouseService mouS;
	private IPcService pcS;
	private IRamServices ramS;
	private ISchedaGraficaServices sgS;
	private ISchedaMadreServices smS;
	private ISistemaRaffreddamentoServices sisS;
	private ITastieraService tS;
	
	
	public ProdottoImpl(IProdottoRepository prodR) {
		this.prodR = prodR;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(ProdottoReq req) throws AcademyException {
		log.debug("create: " + req);
		Prodotto prod = new Prodotto();
		Optional<Prodotto> p = prodR.findByDescrizione(req.getDescrizione());
		if(p.isPresent())
			throw new AcademyException("Prodotto già esistente nel database");
		
		if(req.getDescrizione() == null)
			throw new AcademyException("Descrizione non presente, riprova");
		prod.setDescrizione(req.getDescrizione());
		
		if(req.getCategoria() == null)
			throw new AcademyException("Categoria non presente, riprova");
		prod.setCategoria(req.getCategoria());
		
		if(req.getMarca() == null)
			throw new AcademyException("Marca non presente, riprova");
		prod.setMarca(req.getMarca());
		
		if(req.getCosto() == null || req.getCosto()<0)
			throw new AcademyException("Costo non presente, riprova");
		prod.setCosto(req.getCosto());
		
		if(req.getPrezzo() == null || req.getPrezzo()<0)
			throw new AcademyException("Prezzo non presente, riprova");
		prod.setPrezzo(req.getPrezzo());
		
		if(req.getQuantita() == null || req.getQuantita()<0)
			throw new AcademyException("Quantità errata, riprova");
		prod.setQuantita(req.getQuantita());	
		
		prodR.save(prod);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(ProdottoReq req) throws AcademyException {
		log.debug("update: " + req);
		Optional<Prodotto> p = prodR.findById(req.getId());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non esistente nel database");
		Prodotto prod = p.get();
		

		if(req.getDescrizione() == null) {
			throw new AcademyException("Descrizione non presente, riprova");
		}
		List<Prodotto> lp = prodR.findAll();
		for (Prodotto pr : lp) {
			if(pr.getDescrizione().equalsIgnoreCase(req.getDescrizione())&&pr.getId()!=req.getId())
				throw new AcademyException("Prodotto con la stessa descrizione");
			}
		prod.setDescrizione(req.getDescrizione());
		
		if(req.getCategoria() == null)
			throw new AcademyException("Categoria non presente, riprova");
		prod.setCategoria(req.getCategoria());
		
		if(req.getMarca() == null)
			throw new AcademyException("Marca non presente, riprova");
		prod.setMarca(req.getMarca());
		
		if(req.getCosto() == null || req.getCosto()<0)
			throw new AcademyException("Costo non presente, riprova");
		prod.setCosto(req.getCosto());
		
		if(req.getPrezzo() == null || req.getPrezzo()<0)
			throw new AcademyException("Prezzo non presente, riprova");
		prod.setPrezzo(req.getPrezzo());
		
		if(req.getQuantita() == null || req.getQuantita()<0)
			throw new AcademyException("Quantità errata, riprova");
		prod.setQuantita(req.getQuantita());	
		
		prodR.save(prod);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(ProdottoReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<Prodotto> p = prodR.findById(req.getId());
		
		if(p.isEmpty())
			throw new AcademyException("Prodotto non esistente");
	
		switch (p.get().getCategoria().getDescrizione()) {
		case "Alimentazione": {
			AlimentazioneReq r = new AlimentazioneReq();
			
			r.setId(p.get().getAlimentazione().getId());
			alimS.delete(r);
			prodR.delete(p.get());
			
		}
		case "Case": {
			CaseReq r = new CaseReq();
			
			r.setId(p.get().getCasee().getId());
			csS.delete(r);
			prodR.delete(p.get());
			
		}
		case "Cpu": {
			CpuReq r = new CpuReq();
			
			r.setId(p.get().getCpu().getId());
			cpuS.delete(r);
			prodR.delete(p.get());
			
		}
		case "Laptop": {
			LaptopReq r = new LaptopReq();
			
			r.setId(p.get().getLaptop().getId());
			lapS.delete(r);
			prodR.delete(p.get());
			
		}
		
		case "Memoria": {
			MemoriaReq r = new MemoriaReq();
			
			r.setId(p.get().getMemoria().getId());
			memS.delete(r);
			prodR.delete(p.get());
			
		}
		case "Monitor": {
			MonitorReq r = new MonitorReq();
			
			r.setId(p.get().getMonitor().getId());
			monS.delete(r);
			prodR.delete(p.get());
			
		}
		case "Mouse": {
			MouseReq r = new MouseReq();
			
			r.setId(p.get().getMouse().getId());
			mouS.delete(r);
			prodR.delete(p.get());
			
		}
		case "Pc": {
			PcReq r = new PcReq();
			
			r.setId(p.get().getPc().getId());
			pcS.delete(r);
			prodR.delete(p.get());
			
		}
		case "Ram": {
			RamReq r = new RamReq();
			
			r.setId(p.get().getPc().getId());
			ramS.delete(r);
			prodR.delete(p.get());
			
		}
		case "SchedaGrafica": {
			SchedaGraficaReq r = new SchedaGraficaReq();
			
			r.setId(p.get().getSchedagrafica().getId());
			sgS.delete(r);
			prodR.delete(p.get());
			
		}
		case "SchedaMadre": {
			SchedaMadreReq r = new SchedaMadreReq();
			
			r.setId(p.get().getSchedamadre().getId());
			smS.delete(r);
			prodR.delete(p.get());
			
		}
		case "SistemaRaffreddamento": {
			SistemaRaffreddamentoReq r = new SistemaRaffreddamentoReq();
			
			r.setId(p.get().getSistemaRaffreddamento().getId());
			sisS.delete(r);
			prodR.delete(p.get());
			
		}
		case "Tasiera": {
			TastieraReq r = new TastieraReq();
			
			r.setId(p.get().getTastiera().getId());
			tS.delete(r);
			prodR.delete(p.get());
			
		}
		
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + p.get().getCategoria().getDescrizione());
		}		
		
	}
	
	
	@Override
	public ProdottoDTO getById(Integer id) throws AcademyException {
		log.debug("getProdotto: " + id);
		Optional<Prodotto> prod = prodR.findById(id);
		
		if(prod.isEmpty())
			throw new AcademyException("Prodotto non esistente");
		Prodotto p = prod.get();

		return ProdottoDTO.builder()
				.id(p.getId())
				.descrizione(p.getDescrizione())
				.costo(p.getCosto())
				.prezzo(p.getPrezzo())
				.quantita(p.getQuantita())
				.img(p.getImg())
				.categoria(buildCategoriaDTO(p.getCategoria()))
				.marca(buildMarcaDTO(p.getMarca()))
				.build();
	}

	@Override
	public List<ProdottoDTO> listAll() {
		log.debug("lisAll di Alimentazione: ");
		List<Prodotto> lP = prodR.findAll();
		
		return lP.stream()
				.map(p -> ProdottoDTO.builder()
						.id(p.getId())
						.descrizione(p.getDescrizione())
						.costo(p.getCosto())
						.prezzo(p.getPrezzo())
						.quantita(p.getQuantita())
						.img(p.getImg())
						.categoria(buildCategoriaDTO(p.getCategoria()))
						.marca(buildMarcaDTO(p.getMarca()))
						.build())
				.collect(Collectors.toList());
	}
}
	
