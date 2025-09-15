package com.betacom.bb.requests;

import lombok.Data;

@Data
public class SchedaMadreReq {
	private Integer id;
	private String descrizione;
	private String compatibilita;
	private Integer consumo;
	private Integer idProdotto;
	private Integer idFormato;
	private Integer idPc;

}
