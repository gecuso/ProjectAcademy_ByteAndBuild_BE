package com.betacom.bb.requests;

import lombok.Data;

@Data
public class FormatoReq {
	private Integer id;
	private String descrizione;
	private Integer idSchedaMadre;
	private Integer idCase; //doppia ee perche case da errore
}
