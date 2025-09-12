package com.betacom.bb.requests;

import com.betacom.bb.models.Pc;
import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class SistemaRaffreddamentoReq {
	private Integer id;
	private String descrizione;
	private Integer consumo;
	private Prodotto prodotto;
	private Pc pc;
	
}
