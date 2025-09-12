package com.betacom.bb.requests;

import com.betacom.bb.models.Formato;
import com.betacom.bb.models.Pc;
import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class CaseReq {
	private Integer id;
	private String descrizione;
	private String dimensioni;
	private Formato formato;
	private Prodotto prodotto;
	private Pc pc;

}
