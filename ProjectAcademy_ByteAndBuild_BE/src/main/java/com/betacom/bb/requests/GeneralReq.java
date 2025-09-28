package com.betacom.bb.requests;

import lombok.Data;

@Data
public class GeneralReq {
	private AlimentazioneReq alimReq;
	private ProdottoReq prodReq;
	private PcReq pcReq;
	private CaseReq caseReq;
	private CpuReq cpuReq;
	private LaptopReq laptopReq;
	private MemoriaReq memReq;
	private MonitorReq monitorReq;
	private MouseReq mouseReq;
	private RamReq ramReq;
	private SchedaGraficaReq schGrfReq;
	private SchedaMadreReq schMdrReq;
	private SistemaRaffreddamentoReq sisRafReq;
	private TastieraReq tastReq;
}
