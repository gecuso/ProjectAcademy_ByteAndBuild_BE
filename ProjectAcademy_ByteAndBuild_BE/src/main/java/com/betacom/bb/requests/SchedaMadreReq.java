package com.betacom.bb.requests;

import com.betacom.bb.models.Formato;
import com.betacom.bb.models.Pc;
import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class SchedaMadreReq {
	private Integer id;
	private String descrizione;
	private String compatibilita;
	private Integer consumo;
	private Prodotto prodotto;
	private Formato formato;
	private Pc pc;

}
