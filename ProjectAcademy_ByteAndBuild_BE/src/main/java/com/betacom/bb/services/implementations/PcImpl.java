package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.PcDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Alimentazione;
import com.betacom.bb.models.Case;
import com.betacom.bb.models.Cpu;
import com.betacom.bb.models.Memoria;
import com.betacom.bb.models.Pc;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.models.Ram;
import com.betacom.bb.models.SchedaGrafica;
import com.betacom.bb.models.SchedaMadre;
import com.betacom.bb.models.SistemaRaffreddamento;
import com.betacom.bb.repositories.IAlimentazioneRepository;
import com.betacom.bb.repositories.ICaseRepository;
import com.betacom.bb.repositories.ICpuRepository;
import com.betacom.bb.repositories.IFormatoRepository;
import com.betacom.bb.repositories.IMarcaRepository;
import com.betacom.bb.repositories.IMemoriaRepository;
import com.betacom.bb.repositories.IPcRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.repositories.IRamRepository;
import com.betacom.bb.repositories.ISchedaGraficaRepository;
import com.betacom.bb.repositories.ISchedaMadreRepository;
import com.betacom.bb.repositories.ISistemaRaffreddamentoRepository;
import com.betacom.bb.requests.PcReq;
import com.betacom.bb.services.interfaces.IPcService;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PcImpl extends Utilities implements IPcService{

	private IPcRepository pcR;
	private IAlimentazioneRepository alimR;
	private ICaseRepository caseR;
	private ICpuRepository cpuR;
	private IProdottoRepository prodR;
	private IMemoriaRepository memR;
	private ISchedaGraficaRepository schgrfR;
	private ISchedaMadreRepository schMdrR;
	private ISistemaRaffreddamentoRepository sisRafR;
	private IRamRepository ramR;

	

	public PcImpl(IPcRepository pcR, IAlimentazioneRepository alimR, ICaseRepository caseR, ICpuRepository cpuR,
			IFormatoRepository formR, IMarcaRepository marcaR, IProdottoRepository prodR, IMemoriaRepository memR,
			ISchedaGraficaRepository schgrfR, ISchedaMadreRepository schMdrR, ISistemaRaffreddamentoRepository sisRafR,
			IRamRepository ramR) {
		super();
		this.pcR = pcR;
		this.alimR = alimR;
		this.caseR = caseR;
		this.cpuR = cpuR;
		this.prodR = prodR;
		this.memR = memR;
		this.schgrfR = schgrfR;
		this.schMdrR = schMdrR;
		this.sisRafR = sisRafR;
		this.ramR = ramR;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer create(PcReq pcReq) throws AcademyException {
		
		log.debug("dati PcReq: "+pcReq);
		
		if(pcReq.getIdAlimentazione()==null)throw new AcademyException("Alimentazione nulla");
		if(pcReq.getIdCase()==null)throw new AcademyException("case nulla");
		if(pcReq.getIdCpu()==null)throw new AcademyException("cpu nulla");
		if(pcReq.getDescrizione()==null)throw new AcademyException("descrizione nulla");
		if(pcReq.getIdMemoria()==null)throw new AcademyException("memoria nulla");
		if(pcReq.getIdProdotto()==null)throw new AcademyException("prodotto nulla");
		if(pcReq.getIdRam()==null)throw new AcademyException("ram nulla");
		if(pcReq.getIdSchedaGrafica()==null)throw new AcademyException("scheda grafica nulla");
		if(pcReq.getIdSchedaMadre()==null)throw new AcademyException("scheda madre nulla");
		if(pcReq.getIdSistemaRaffreddamento()==null)throw new AcademyException("sistema di raffreddamento nulla");
		
		Optional<Alimentazione> alim = alimR.findById(pcReq.getIdAlimentazione());
		if(alim.isEmpty())throw new AcademyException("alimentazione non esistente");
		
		Optional<Case> casee = caseR.findById(pcReq.getIdCase());
		if(casee.isEmpty())throw new AcademyException("case non esistente");
		
		Optional<Cpu> cpu = cpuR.findById(pcReq.getIdCpu());
		if(cpu.isEmpty())throw new AcademyException("cpu non esistente");
		
		Optional<Memoria> mem = memR.findById(pcReq.getIdMemoria());
		if(mem.isEmpty())throw new AcademyException("memoria non esistente");
		
		Optional<Prodotto> prod = prodR.findById(pcReq.getIdProdotto());
		if(prod.isEmpty())throw new AcademyException("prodotto non esistente");

		Optional<Ram> ram = ramR.findById(pcReq.getIdRam());
		if(ram.isEmpty())throw new AcademyException("ram non esistente");

		Optional<SchedaGrafica> schgraf = schgrfR.findById(pcReq.getIdSchedaGrafica());
		if(schgraf.isEmpty())throw new AcademyException("scheda grafica non esistente");

		Optional<SchedaMadre> schmdr = schMdrR.findById(pcReq.getIdSchedaMadre());
		if(schmdr.isEmpty())throw new AcademyException("scheda madre non esistente");

		Optional<SistemaRaffreddamento> sisRaf = sisRafR.findById(pcReq.getIdSistemaRaffreddamento());
		if(sisRaf.isEmpty())throw new AcademyException("sistema di raffreddamento non esistente");
		
		Optional<Pc> m = pcR.findByDescrizione(pcReq.getDescrizione());
		if(m.isPresent()) throw new AcademyException("pc con descrizione :"+pcReq.getDescrizione()+" è gia esistente ");
		
		Pc c = new Pc();
		
		if(!controlloFormato(schMdrR.getById(pcReq.getIdSchedaMadre()).getFormato().getDescrizione(), caseR.getById(pcReq.getIdCase()).getFormato().getDescrizione()))
		{
			throw new AcademyException("case e scheda madre incompatibili, formati diversi");
		}
		if(!controlloCompatibilita(schMdrR.getById(pcReq.getIdSchedaMadre()).getCompatibilita(), cpuR.getById(pcReq.getIdCpu()).getCompatibilita()))
		{
			throw new AcademyException("Processore e scheda madre incompatibili, compatibilità diverse");
		}
		if(controlloAlimentazione(pcReq))
		{
			throw new AcademyException("gli elementi consumano troppa potenza, scegliere un alimentatore piu potente");
		}
		if(controlloQuantita(prodR.getById(pcReq.getIdProdotto()).getQuantita(),pcReq))
		{
			riduciQuantita(prodR.getById(pcReq.getIdProdotto()).getQuantita(),pcReq);
		}
		else throw new AcademyException("elementi non sufficienti");

		Integer consumoTot =cpu.get().getConsumo()+sisRaf.get().getConsumo()+
				ram.get().getConsumo()+schgraf.get().getConsumo()+schmdr.get().getConsumo();
		
		c.setCasee(casee.get());
		c.setCpu(cpu.get());
		c.setMemoria(mem.get());
		c.setSistemaRaffreddamento(sisRaf.get());
		c.setAlimentazione(alim.get());
		c.setRam(ram.get());
		c.setSchedaGrafica(schgraf.get());
		c.setSchedaMadre(schmdr.get());
		c.setDescrizione(pcReq.getDescrizione());
		c.setProdotto(prod.get());
		c.setTotConsumo(consumoTot);
		
		Integer costoTot= alim.get().getProdotto().getCosto() + casee.get().getProdotto().getCosto() + cpu.get().getProdotto().getCosto() +
						  mem.get().getProdotto().getCosto() + sisRaf.get().getProdotto().getCosto() + ram.get().getProdotto().getCosto() +
						  schgraf.get().getProdotto().getCosto() + schmdr.get().getProdotto().getCosto();


		prod.get().setCosto(consumoTot);
		
		return pcR.save(c).getId();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(PcReq pcReq) throws AcademyException {
		Optional<Pc> m = pcR.findById(pcReq.getId());
		if(!m.isPresent()) throw new AcademyException("pc non esistente");
		
		if(m.get().getProdotto().getQuantita()>0)
		{
			aumentaQuantita(m.get().getProdotto().getQuantita(),pcReq);
		}
		
		pcR.delete(m.get());	
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(PcReq pcReq) throws AcademyException {
		if(pcReq.getId()==null) throw new AcademyException("necessario l'id del pc per modificarlo");
		Optional<Pc> m = pcR.findById(pcReq.getId());
		Integer oldId=pcReq.getId();
		if(!m.isPresent()) throw new AcademyException("pc non esistente");
		
		if(pcReq.getIdAlimentazione()==null)throw new AcademyException("Alimentazione nulla");
		if(pcReq.getIdCase()==null)throw new AcademyException("case nulla");
		if(pcReq.getIdCpu()==null)throw new AcademyException("cpu nulla");
		if(pcReq.getDescrizione()==null)throw new AcademyException("descrizione nulla");
		if(pcReq.getIdMemoria()==null)throw new AcademyException("memoria nulla");
		if(pcReq.getIdProdotto()==null)throw new AcademyException("prodotto nulla");
		if(pcReq.getIdRam()==null)throw new AcademyException("ram nulla");
		if(pcReq.getIdSchedaGrafica()==null)throw new AcademyException("scheda grafica nulla");
		if(pcReq.getIdSchedaMadre()==null)throw new AcademyException("scheda madre nulla");
		if(pcReq.getIdSistemaRaffreddamento()==null)throw new AcademyException("sistema di raffreddamento nulla");
		
		Optional<Alimentazione> alim = alimR.findById(pcReq.getIdAlimentazione());
		if(alim.isEmpty())throw new AcademyException("alimentazione non esistente");
		
		Optional<Case> casee = caseR.findById(pcReq.getIdCase());
		if(casee.isEmpty())throw new AcademyException("case non esistente");
		
		Optional<Cpu> cpu = cpuR.findById(pcReq.getIdCpu());
		if(cpu.isEmpty())throw new AcademyException("cpu non esistente");
		
		Optional<Memoria> mem = memR.findById(pcReq.getIdMemoria());
		if(mem.isEmpty())throw new AcademyException("memoria non esistente");
		
		Optional<Prodotto> prod = prodR.findById(pcReq.getIdProdotto());
		if(prod.isEmpty())throw new AcademyException("prodotto non esistente");

		Optional<Ram> ram = ramR.findById(pcReq.getIdRam());
		if(ram.isEmpty())throw new AcademyException("ram non esistente");

		Optional<SchedaGrafica> schgraf = schgrfR.findById(pcReq.getIdSchedaGrafica());
		if(schgraf.isEmpty())throw new AcademyException("scheda grafica non esistente");

		Optional<SchedaMadre> schmdr = schMdrR.findById(pcReq.getIdSchedaMadre());
		if(schmdr.isEmpty())throw new AcademyException("scheda madre non esistente");

		Optional<SistemaRaffreddamento> sisRaf = sisRafR.findById(pcReq.getIdSistemaRaffreddamento());
		if(sisRaf.isEmpty())throw new AcademyException("sistema di raffreddamento non esistente");
		
		if(!controlloFormato(schMdrR.getById(pcReq.getIdSchedaMadre()).getFormato().getDescrizione(), caseR.getById(pcReq.getIdCase()).getFormato().getDescrizione()))
		{
			throw new AcademyException("case e scheda madre incompatibili, formati diversi");
		}
		if(!controlloCompatibilita(schMdrR.getById(pcReq.getIdSchedaMadre()).getCompatibilita(), cpuR.getById(pcReq.getIdCpu()).getCompatibilita()))
		{
			throw new AcademyException("Processore e scheda madre incompatibili, compatibilità diverse");
		}
		if(controlloAlimentazione(pcReq))
		{
			throw new AcademyException("gli elementi consumano troppa potenza, scegliere un alimentatore piu potente");
		}
		if(controlloQuantita(prodR.getById(pcReq.getIdProdotto()).getQuantita(),pcReq))
		{
			riduciQuantita(prodR.getById(pcReq.getIdProdotto()).getQuantita(),pcReq);
		}
		else throw new AcademyException("elementi non sufficienti");

		
		try {
			aumentaQuantita(m.get().getProdotto().getQuantita(), pcReq);
			List<Pc> lp = pcR.findAll();
			for (Pc pc : lp) {
				if(pc.getDescrizione().equalsIgnoreCase(pcReq.getDescrizione()) && pc.getId() != oldId)
					throw new AcademyException("Esiste già un PC con questa descrione");
			}
			pcR.delete(m.get());
			Integer newId = create(pcReq);
			m = pcR.findById(newId);
			m.get().setId(oldId);
			pcR.save(m.get());
		} catch (Exception e) {
			throw new AcademyException(e.getMessage());
		}	
		
	}
	
	@Override
	public PcDTO getById(Integer id) throws AcademyException
	{
		log.debug("getPc :" + id);
		Optional<Pc> pcO = pcR.findById(id);
		
		if (pcO.isEmpty())
			throw new AcademyException("pc non trovato in database :" + id);
		Pc p = pcO.get();
	
		return PcDTO.builder()
				.id(p.getId())
				.descrizione(p.getDescrizione())
				.totConsumo(p.getTotConsumo())
				.prodotto(buildProdottoDTO(p.getProdotto()))
				.schedaMadre(buildSchedaMadreDTO(p.getSchedaMadre()))
				.schedaGrafica(buildSchedaGraficaDTO(p.getSchedaGrafica()))
				.cpu(buildCpuDTO(p.getCpu()))
				.ram(buildRamDTO(p.getRam()))
				.memoria(buildMemoriaDTO(p.getMemoria()))
				.casee(buildCaseDTO(p.getCasee()))
				.sistemaRaffreddamento(buildSistemaRaffreddamentoDTO(p.getSistemaRaffreddamento()))
				.alimentazione(buildAlimentazioneDTO(p.getAlimentazione()))
				.build();
	}
	
	@Override
	public List<PcDTO> listAll() throws AcademyException {
		List<Pc> lp = pcR.findAll();
		
		return buildListPcDTO(lp);
	}

	@Override
	public Boolean controlloFormato(String form1, String form2) {
		return form1.equalsIgnoreCase(form2);
	}
	@Override
	public Boolean controlloCompatibilita(String comp1,String comp2)
	{
		return comp1.equalsIgnoreCase(comp2);
	}
	@Override
	public Boolean controlloQuantita(Integer n, PcReq pcReq) throws AcademyException {
		
		if(pcReq.getIdAlimentazione()!=null)
		if(alimR.getById(pcReq.getIdAlimentazione()).getProdotto().getQuantita()<n) {
			throw new AcademyException("alimentatori non sufficienti");
		}
		if(pcReq.getIdCase()!=null)
		if(caseR.getById(pcReq.getIdCase()).getProdotto().getQuantita()<n) {
			throw new AcademyException("Case non sufficienti");
		}
		if(pcReq.getIdCpu()!=null)
		if(cpuR.getById(pcReq.getIdCpu()).getProdotto().getQuantita()<n) {
			throw new AcademyException("Processori non sufficienti");
		}
		if(pcReq.getIdMemoria()!=null)
		if(memR.getById(pcReq.getIdMemoria()).getProdotto().getQuantita()<n) {
			throw new AcademyException("schede di memoria non sufficienti");
		}
		if(pcReq.getIdRam()!=null)
		if(ramR.getById(pcReq.getIdRam()).getProdotto().getQuantita()<n) {
			throw new AcademyException("schede di memoria RAM non sufficienti");
		}	
		if(pcReq.getIdSchedaGrafica()!=null)
		if(schgrfR.getById(pcReq.getIdSchedaGrafica()).getProdotto().getQuantita()<n) {
			throw new AcademyException("schede grafiche non sufficienti");
		}
		if(pcReq.getIdSchedaMadre()!=null)
		if(schMdrR.getById(pcReq.getIdSchedaMadre()).getProdotto().getQuantita()<n) {
			throw new AcademyException("scheda madre non sufficienti");
		}
		if(pcReq.getIdSistemaRaffreddamento()!=null)
			if(sisRafR.getById(pcReq.getIdSistemaRaffreddamento()).getProdotto().getQuantita()<n) {
				throw new AcademyException("moduli del sistema di raffreddamento non sufficienti");
			}	
		
		return true;
	}
	
	
	@Override
	public Boolean controlloAlimentazione(PcReq pcReq) throws AcademyException {
		
		Integer consumoTot =cpuR.getById(pcReq.getIdCpu()).getConsumo()+sisRafR.getById(pcReq.getIdSistemaRaffreddamento()).getConsumo()+
				ramR.getById(pcReq.getIdRam()).getConsumo()+schgrfR.getById(pcReq.getIdSchedaGrafica()).getConsumo()+schMdrR.getById(pcReq.getIdSchedaMadre()).getConsumo();
		
		consumoTot=(int) (consumoTot*1.5);
		
		log.debug("consumotot: "+consumoTot);
		
		return consumoTot>alimR.getById(pcReq.getIdAlimentazione()).getPotenza();
	} 
	@Override
	public void riduciQuantita(Integer n,PcReq pcReq)
	{
		
		sisRafR.getById(pcReq.getIdSistemaRaffreddamento()).getProdotto().setQuantita(sisRafR.getById(pcReq.getIdSistemaRaffreddamento()).getProdotto().getQuantita()-n);
		prodR.save(sisRafR.getById(pcReq.getIdSistemaRaffreddamento()).getProdotto());
		
		alimR.getById(pcReq.getIdAlimentazione()).getProdotto().setQuantita(alimR.getById(pcReq.getIdAlimentazione()).getProdotto().getQuantita()-n);
		prodR.save(alimR.getById(pcReq.getIdAlimentazione()).getProdotto());
		
		caseR.getById(pcReq.getIdCase()).getProdotto().setQuantita(caseR.getById(pcReq.getIdCase()).getProdotto().getQuantita()-n);
		prodR.save(caseR.getById(pcReq.getIdCase()).getProdotto());
		
		cpuR.getById(pcReq.getIdCpu()).getProdotto().setQuantita(cpuR.getById(pcReq.getIdCpu()).getProdotto().getQuantita()-n);
		prodR.save(cpuR.getById(pcReq.getIdCpu()).getProdotto());
		
		memR.getById(pcReq.getIdMemoria()).getProdotto().setQuantita(memR.getById(pcReq.getIdMemoria()).getProdotto().getQuantita()-n);
		prodR.save(memR.getById(pcReq.getIdMemoria()).getProdotto());
		
		ramR.getById(pcReq.getIdRam()).getProdotto().setQuantita(ramR.getById(pcReq.getIdRam()).getProdotto().getQuantita()-n);
		prodR.save(ramR.getById(pcReq.getIdRam()).getProdotto());
		
		schgrfR.getById(pcReq.getIdSchedaGrafica()).getProdotto().setQuantita(schgrfR.getById(pcReq.getIdSchedaGrafica()).getProdotto().getQuantita()-n);
		prodR.save(schgrfR.getById(pcReq.getIdSchedaGrafica()).getProdotto());
		
		schMdrR.getById(pcReq.getIdSchedaMadre()).getProdotto().setQuantita(schMdrR.getById(pcReq.getIdSchedaMadre()).getProdotto().getQuantita()-n);
		prodR.save(schMdrR.getById(pcReq.getIdSchedaMadre()).getProdotto());
	}
	@Override
	public void aumentaQuantita(Integer n, PcReq pcReq) {
		sisRafR.getById(pcReq.getIdSistemaRaffreddamento()).getProdotto().setQuantita(sisRafR.getById(pcReq.getIdSistemaRaffreddamento()).getProdotto().getQuantita()+n);
		prodR.save(sisRafR.getById(pcReq.getIdSistemaRaffreddamento()).getProdotto());
		
		alimR.getById(pcReq.getIdAlimentazione()).getProdotto().setQuantita(alimR.getById(pcReq.getIdAlimentazione()).getProdotto().getQuantita()+n);
		prodR.save(alimR.getById(pcReq.getIdAlimentazione()).getProdotto());
		
		caseR.getById(pcReq.getIdCase()).getProdotto().setQuantita(caseR.getById(pcReq.getIdCase()).getProdotto().getQuantita()+n);
		prodR.save(caseR.getById(pcReq.getIdCase()).getProdotto());
		
		cpuR.getById(pcReq.getIdCpu()).getProdotto().setQuantita(cpuR.getById(pcReq.getIdCpu()).getProdotto().getQuantita()+n);
		prodR.save(cpuR.getById(pcReq.getIdCpu()).getProdotto());
		
		memR.getById(pcReq.getIdMemoria()).getProdotto().setQuantita(memR.getById(pcReq.getIdMemoria()).getProdotto().getQuantita()+n);
		prodR.save(memR.getById(pcReq.getIdMemoria()).getProdotto());
		
		ramR.getById(pcReq.getIdRam()).getProdotto().setQuantita(ramR.getById(pcReq.getIdRam()).getProdotto().getQuantita()+n);
		prodR.save(ramR.getById(pcReq.getIdRam()).getProdotto());
		
		schgrfR.getById(pcReq.getIdSchedaGrafica()).getProdotto().setQuantita(schgrfR.getById(pcReq.getIdSchedaGrafica()).getProdotto().getQuantita()+n);
		prodR.save(schgrfR.getById(pcReq.getIdSchedaGrafica()).getProdotto());
		
		schMdrR.getById(pcReq.getIdSchedaMadre()).getProdotto().setQuantita(schMdrR.getById(pcReq.getIdSchedaMadre()).getProdotto().getQuantita()+n);
		prodR.save(schMdrR.getById(pcReq.getIdSchedaMadre()).getProdotto());
	}
	
	

	

	
	
}
