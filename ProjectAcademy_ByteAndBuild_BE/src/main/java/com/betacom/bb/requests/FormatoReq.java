package com.betacom.bb.requests;

import com.betacom.bb.models.Case;
import com.betacom.bb.models.SchedaMadre;

import lombok.Data;

@Data
public class FormatoReq {
	private Integer id;
	private String descrizione;
	private SchedaMadre schedaMadre;
	private Case casee; //doppia ee perche case da errore
}
