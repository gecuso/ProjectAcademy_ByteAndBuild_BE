package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MonitorDTO {

	private Integer id;
//	private String descrizione;
	private String risoluzione;
	private String latenza;
	private String frequenza;
	private ProdottoDTO prodotto;
	
}
