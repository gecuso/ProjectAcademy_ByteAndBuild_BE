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

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PcImpl implements IPcService{

	private IPcRepository pcR;
	private IAlimentazioneRepository alimR;
	private ICaseRepository caseR;
	private ICpuRepository cpuR;
	private IFormatoRepository formR;
	private IMarcaRepository marcaR;
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
		this.formR = formR;
		this.marcaR = marcaR;
		this.prodR = prodR;
		this.memR = memR;
		this.schgrfR = schgrfR;
		this.schMdrR = schMdrR;
		this.sisRafR = sisRafR;
		this.ramR = ramR;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(PcReq pcReq) throws AcademyException {
		
		log.debug("dati PcReq: "+pcReq);
		
		if(pcReq.getAlimentazione()==null)throw new AcademyException("Alimentazione nulla");
		if(pcReq.getCasee()==null)throw new AcademyException("case nulla");
		if(pcReq.getCpu()==null)throw new AcademyException("cpu nulla");
		if(pcReq.getDescrizione()==null)throw new AcademyException("descrizione nulla");
		if(pcReq.getMemoria()==null)throw new AcademyException("memoria nulla");
		if(pcReq.getProdotto()==null)throw new AcademyException("prodotto nulla");
		if(pcReq.getRam()==null)throw new AcademyException("ram nulla");
		if(pcReq.getSchedaGrafica()==null)throw new AcademyException("scheda grafica nulla");
		if(pcReq.getSchedaMadre()==null)throw new AcademyException("scheda madre nulla");
		if(pcReq.getSistemaRaffreddamento()==null)throw new AcademyException("sistema di raffreddamento nulla");
		
		Optional<Alimentazione> alim = alimR.findById(pcReq.getAlimentazione().getId());
		if(alim.isEmpty())throw new AcademyException("alimentazione non esistente");
		
		Optional<Case> casee = caseR.findById(pcReq.getCasee().getId());
		if(casee.isEmpty())throw new AcademyException("case non esistente");
		
		Optional<Cpu> cpu = cpuR.findById(pcReq.getCpu().getId());
		if(cpu.isEmpty())throw new AcademyException("cpu non esistente");
		
		Optional<Memoria> mem = memR.findById(pcReq.getMemoria().getId());
		if(mem.isEmpty())throw new AcademyException("memoria non esistente");
		
		Optional<Prodotto> prod = prodR.findById(pcReq.getProdotto().getId());
		if(prod.isEmpty())throw new AcademyException("prodotto non esistente");

		Optional<Ram> ram = ramR.findById(pcReq.getRam().getId());
		if(ram.isEmpty())throw new AcademyException("ram non esistente");

		Optional<SchedaGrafica> schgraf = schgrfR.findById(pcReq.getSchedaGrafica().getId());
		if(schgraf.isEmpty())throw new AcademyException("scheda grafica non esistente");

		Optional<SchedaMadre> schmdr = schMdrR.findById(pcReq.getSchedaMadre().getId());
		if(schmdr.isEmpty())throw new AcademyException("scheda madre non esistente");

		Optional<SistemaRaffreddamento> sisRaf = sisRafR.findById(pcReq.getProdotto().getId());
		if(prod.isEmpty())throw new AcademyException("prodotto non esistente");
		
		Optional<Pc> m = pcR.findByDescrizione(pcReq.getDescrizione());
		if(m.isPresent()) throw new AcademyException("pc con descrizione :"+pcReq.getDescrizione()+" è gia esistente ");
		
		Pc c = new Pc();
		
		if(!controlloFormato(pcReq.getSchedaMadre().getFormato().getDescrizione(), pcReq.getCasee().getFormato().getDescrizione()))
		{
			throw new AcademyException("case e scheda madre incompatibili, formati diversi");
		}
		if(!controlloCompatibilita(pcReq.getSchedaMadre().getCompatibilita(), pcReq.getCpu().getCompatibilita()))
		{
			throw new AcademyException("Processore e scheda madre incompatibili, compatibilità diverse");
		}
		if(!controlloAlimentazione(pcReq))
		{
			throw new AcademyException("gli elementi consumano troppa potenza, scegliere un alimentatore piu potente");
		}
		if(controlloQuantita(pcReq.getProdotto().getQuantita(),pcReq))
		{
			riduciQuantita(pcReq.getProdotto().getQuantita(),pcReq);
		}
		else throw new AcademyException("elementi non sufficienti");

		Integer consumoTot =pcReq.getCpu().getConsumo()+pcReq.getSistemaRaffreddamento().getConsumo()+
				pcReq.getRam().getConsumo()+pcReq.getSchedaGrafica().getConsumo()+pcReq.getSchedaMadre().getConsumo();
		
		c.setCasee(pcReq.getCasee());
		c.setCpu(pcReq.getCpu());
		c.setMemoria(pcReq.getMemoria());
		c.setSistemaRaffreddamento(pcReq.getSistemaRaffreddamento());
		c.setAlimentazione(pcReq.getAlimentazione());
		c.setRam(pcReq.getRam());
		c.setSchedaGrafica(pcReq.getSchedaGrafica());
		c.setSchedaMadre(pcReq.getSchedaMadre());
		c.setDescrizione(pcReq.getDescrizione());
		c.setProdotto(pcReq.getProdotto());
		c.setTotConsumo(consumoTot);
		
		pcR.save(c);
			
		/*
		Macchina c = new Macchina();
		c.setCilindrata(carReq.getCilindrata());
		c.setNumeroPorte(carReq.getNumeroPorte());
		c.setTarga(carReq.getTarga());
		Integer id =carR.save(c).getIdMacchina();
		c.setIdMacchina(id);
		
		VeicoloImpl veiImpl= new VeicoloImpl(veiR, motoR, carR, biciR, aliR, catR, colR, marcaR, tipoR, sospR);
		veiImpl.create(veiReq);
		
		List<Veicolo> lv = veiR.findAll();
        Veicolo v = lv.getLast();
        c.setVeicolo(v);
        carR.save(c);
        v.setMacchina(c);
        
        veiR.save(v);
		*/
	}

	@Override
	public List<PcDTO> listAll() throws AcademyException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Boolean controlloFormato(String form1, String form2) {
		return form1.equalsIgnoreCase(form2);
	}
	@Override
	public Boolean controlloCompatibilita(String comp1,String comp2)
	{
		return comp1.equals(comp2);
	}


	@Override
	public Boolean controlloQuantita(Integer n, PcReq pcReq) throws AcademyException {
		
		if(pcReq.getAlimentazione().getProdotto().getQuantita()<n) {
			throw new AcademyException("alimentatori non sufficienti");
		}
		if(pcReq.getCasee().getProdotto().getQuantita()<n) {
			throw new AcademyException("Case non sufficienti");
		}
		if(pcReq.getCpu().getProdotto().getQuantita()<n) {
			throw new AcademyException("Processori non sufficienti");
		}
		if(pcReq.getMemoria().getProdotto().getQuantita()<n) {
			throw new AcademyException("schede di memoria non sufficienti");
		}
		if(pcReq.getRam().getProdotto().getQuantita()<n) {
			throw new AcademyException("schede di memoria RAM non sufficienti");
		}	
		if(pcReq.getSchedaGrafica().getProdotto().getQuantita()<n) {
			throw new AcademyException("schede grafiche non sufficienti");
		}
		if(pcReq.getSchedaMadre().getProdotto().getQuantita()<n) {
			throw new AcademyException("scheda madre non sufficienti");
		}
		
		return true;
	}

	@Override
	public Boolean controlloAlimentazione(PcReq pcReq) throws AcademyException {
		
		Integer consumoTot =pcReq.getCpu().getConsumo()+pcReq.getSistemaRaffreddamento().getConsumo()+
				pcReq.getRam().getConsumo()+pcReq.getSchedaGrafica().getConsumo()+pcReq.getSchedaMadre().getConsumo();
		
		consumoTot=(int) (consumoTot*1.5);
		
		return consumoTot<pcReq.getAlimentazione().getPotenza();
	} 
	
	@Override
	public void riduciQuantita(Integer n,PcReq pcReq)
	{

		pcReq.getSistemaRaffreddamento().getProdotto().setQuantita(pcReq.getSistemaRaffreddamento().getProdotto().getQuantita()-n);
		prodR.save(pcReq.getSistemaRaffreddamento().getProdotto());
		
		pcReq.getAlimentazione().getProdotto().setQuantita(pcReq.getAlimentazione().getProdotto().getQuantita()-n);
		prodR.save(pcReq.getAlimentazione().getProdotto());
		
		pcReq.getCasee().getProdotto().setQuantita(pcReq.getCasee().getProdotto().getQuantita()-n);
		prodR.save(pcReq.getCasee().getProdotto());
		
		pcReq.getCpu().getProdotto().setQuantita(pcReq.getCpu().getProdotto().getQuantita()-n);
		prodR.save(pcReq.getCpu().getProdotto());
		
		pcReq.getMemoria().getProdotto().setQuantita(pcReq.getMemoria().getProdotto().getQuantita()-n);
		prodR.save(pcReq.getMemoria().getProdotto());
		
		pcReq.getRam().getProdotto().setQuantita(pcReq.getRam().getProdotto().getQuantita()-n);
		prodR.save(pcReq.getRam().getProdotto());
		
		pcReq.getSchedaGrafica().getProdotto().setQuantita(pcReq.getSchedaGrafica().getProdotto().getQuantita()-n);
		prodR.save(pcReq.getSchedaGrafica().getProdotto());
		
		pcReq.getSchedaMadre().getProdotto().setQuantita(pcReq.getSchedaMadre().getProdotto().getQuantita()-n);
		prodR.save(pcReq.getSchedaMadre().getProdotto());
	}
	
}
