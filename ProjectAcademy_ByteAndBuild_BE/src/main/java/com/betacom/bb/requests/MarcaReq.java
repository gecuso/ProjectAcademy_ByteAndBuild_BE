package com.betacom.bb.requests;

import lombok.Data;

@Data
public class MarcaReq {
	private Integer id;
	private String descrizione;
//	private Prodotto prodotto;
	private Integer idCategoria;
}
