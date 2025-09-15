package com.betacom.bb.requests;

import lombok.Data;

@Data
public class TastieraReq {
	private Integer id;
	private String descrizione;
	private String tipologia;
	private String collegamento;
	private Integer idProdotto;
	
}
