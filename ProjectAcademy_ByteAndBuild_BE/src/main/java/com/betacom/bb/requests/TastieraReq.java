package com.betacom.bb.requests;

import com.betacom.bb.models.Prodotto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TastieraReq {
	private Integer id;
	private String descrizione;
	private String tipologia;
	private String collegamento;
	private Prodotto prodotto;
	
}
