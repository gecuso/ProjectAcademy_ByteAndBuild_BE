package com.betacom.bb.requests;

import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class MouseReq {
	private Integer id;
//	private String descrizione;
	private String collegamento;
	private Prodotto prodotto;
}
