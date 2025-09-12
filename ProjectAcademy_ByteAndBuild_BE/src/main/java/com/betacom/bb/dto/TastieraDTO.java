package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TastieraDTO {

	private Integer id;
	private String descrizione;
	private String tipologia;
	private String collegamento;
	private ProdottoDTO prodotto;
	
}
