package com.betacom.bb.requests;

import lombok.Data;

@Data
public class SchedaGraficaReq {
	private Integer id;
	private String descrizione;
	private Integer consumo;
	private Integer idProdotto;
	private Integer idPc;
}
