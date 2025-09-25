package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CarrelloDTO {

	private Integer id;
	private Integer numeroProdotti;
	private Integer prezzoTotale;
	private UtenteDTO utente;
	
}
