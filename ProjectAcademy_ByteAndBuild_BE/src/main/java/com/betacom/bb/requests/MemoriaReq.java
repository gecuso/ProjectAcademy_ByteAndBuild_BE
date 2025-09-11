package com.betacom.bb.requests;

import com.betacom.bb.models.Pc;
import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class MemoriaReq {
	private Integer id;
	private String descrizione;
	private String marca;
	private Integer spazio;
	private Prodotto prodotto;
	private Pc pc;
}
