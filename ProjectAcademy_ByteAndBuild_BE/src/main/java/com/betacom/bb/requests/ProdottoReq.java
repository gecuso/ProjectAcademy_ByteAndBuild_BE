package com.betacom.bb.requests;

import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Marca;

import lombok.Data;

@Data
public class ProdottoReq {

	private Integer id;
	private String descrizione;
	private Integer costo;
	private Integer prezzo;
	private Integer quantita;
	private String img;
	private Categoria categoria;
	private Marca marca; 
	
}
