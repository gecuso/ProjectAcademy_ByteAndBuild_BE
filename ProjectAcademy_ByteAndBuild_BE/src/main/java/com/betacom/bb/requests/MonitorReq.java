package com.betacom.bb.requests;

import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class MonitorReq {
	private Integer id;
	private String descrizione;
	private String risoluzione;
	private String latenza;
	private String frequenza;
	private Prodotto prodotto;

}
