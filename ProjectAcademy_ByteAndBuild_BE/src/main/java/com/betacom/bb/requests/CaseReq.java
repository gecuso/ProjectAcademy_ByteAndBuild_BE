package com.betacom.bb.requests;

import lombok.Data;

@Data
public class CaseReq {
	private Integer id;
	private String descrizione;
	private String dimensioni;
	private Integer idFormato;
	private Integer idProdotto;
	private Integer idPc;

}
