package com.betacom.bb.requests;

import com.betacom.bb.models.Marca;
import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class CategoriaReq {
	private Integer id;
	private String descrizione;
	private Prodotto prodotto;
	private Marca marca; 

}
