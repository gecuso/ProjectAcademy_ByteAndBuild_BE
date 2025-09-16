package com.betacom.bb.requests;

import lombok.Data;

@Data
public class AlimentazioneReq {
	private Integer id;
	private String descrizione;
	private Integer potenza;
	private Integer idProdotto;
//	private Integer idPc;
}
