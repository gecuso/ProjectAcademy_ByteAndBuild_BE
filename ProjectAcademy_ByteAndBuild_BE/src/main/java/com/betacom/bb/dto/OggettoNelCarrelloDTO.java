package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OggettoNelCarrelloDTO {

	private Integer id;
	private Integer quantita;
	private CarrelloDTO carrello;
	private ProdottoDTO prodotto;
	
}
