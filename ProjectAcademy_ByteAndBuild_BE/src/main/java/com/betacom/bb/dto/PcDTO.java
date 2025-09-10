package com.betacom.bb.dto;

import java.util.List;

import lombok.Data;

@Data
public class PcDTO {

	private Integer id;
	private String descrizione;
	private Integer totConsumo;
	private  List<SchedaMadreDTO> schedaMadre;
	private  List<SchedaGraficaDTO> schedaGrafica;
	private  List<CpuDTO> cpu;
	private  List<RamDTO> ram;
	private  List<MemoriaDTO> memoria;
	private  List<CaseDTO> casee;
	private  List<SistemaRaffreddamentoDTO> sistemaRaffreddamento;
	private  List<AlimentazioneDTO> alimentazione;
	
}
