package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MouseDTO {

	private Integer id;
	private String descrizione;
	private String collegamento;
	private ProdottoDTO prodotto;
	
}
