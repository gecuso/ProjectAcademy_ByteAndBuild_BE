package com.betacom.bb.requests;

import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class MarcaReq {
	private Integer id;
	private String descrizione;
	private Prodotto prodotto;
	private Categoria categoria;
}
