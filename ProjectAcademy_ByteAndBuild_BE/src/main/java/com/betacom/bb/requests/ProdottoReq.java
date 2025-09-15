package com.betacom.bb.requests;

import lombok.Data;

@Data
public class ProdottoReq {

	private Integer id;
	private String descrizione;
	private Integer costo;
	private Integer prezzo;
	private Integer quantita;
	private String img;
	private Integer idCategoria;
	private Integer idMarca; 
	
}
