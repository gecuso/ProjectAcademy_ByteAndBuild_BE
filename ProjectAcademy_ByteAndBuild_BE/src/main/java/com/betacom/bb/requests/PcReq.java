package com.betacom.bb.requests;

import com.betacom.bb.models.Alimentazione;
import com.betacom.bb.models.Case;
import com.betacom.bb.models.Cpu;
import com.betacom.bb.models.Memoria;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.models.Ram;
import com.betacom.bb.models.SchedaGrafica;
import com.betacom.bb.models.SchedaMadre;
import com.betacom.bb.models.SistemaRaffreddamento;

import lombok.Data;

@Data
public class PcReq {

	private Integer id;
	private String descrizione;
	private Integer totConsumo;
	private Prodotto prodotto;
	////////////////////////////////
	
	private  SchedaMadre schedaMadre;
	private  SchedaGrafica schedaGrafica;
	private  Cpu cpu;
	private  Ram ram;
	private  Memoria memoria;
	private  Case casee;
	private  SistemaRaffreddamento sistemaRaffreddamento;
	private  Alimentazione alimentazione;
}
