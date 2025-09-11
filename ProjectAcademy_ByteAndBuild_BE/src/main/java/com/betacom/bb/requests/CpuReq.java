package com.betacom.bb.requests;

import com.betacom.bb.models.Pc;
import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class CpuReq {
	private Integer id;
	private String descrizione;
	private String marca;
	private String compatibilita;
	private Integer consumo;
	private Prodotto prodotto;
	private Pc pc;
}
