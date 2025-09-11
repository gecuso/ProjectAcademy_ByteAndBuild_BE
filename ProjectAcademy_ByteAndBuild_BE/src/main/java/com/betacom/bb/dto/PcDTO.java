package com.betacom.bb.dto;

import lombok.Data;

@Data
public class PcDTO {

	private Integer id;
	private String descrizione;
	private Integer totConsumo;
	private SchedaMadreDTO schedaMadre;
	private SchedaGraficaDTO schedaGrafica;
	private CpuDTO cpu;
	private RamDTO ram;
	private MemoriaDTO memoria;
	private CaseDTO casee;
	private SistemaRaffreddamentoDTO sistemaRaffreddamento;
	private AlimentazioneDTO alimentazione;
	
}
