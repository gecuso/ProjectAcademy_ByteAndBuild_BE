package com.betacom.bb.requests;

import lombok.Data;

@Data
public class CarrelloReq {

	private Integer id;
	private Integer numeroProdotti;
	private Integer prezzoTotale;
	private Integer idUtente;
	
}
