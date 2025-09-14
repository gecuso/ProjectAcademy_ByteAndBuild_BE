package com.betacom.bb.requests;

import com.betacom.bb.models.Pc;
import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class AlimentazioneReq {
	private Integer id;
//	private String descrizione;
	private Integer potenza;
	private Prodotto prodotto;
	private Pc pc;
}
