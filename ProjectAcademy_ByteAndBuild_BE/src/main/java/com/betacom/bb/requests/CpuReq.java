package com.betacom.bb.requests;

import lombok.Data;

@Data
public class CpuReq {
	private Integer id;
	private String descrizione;
//	private String marca;
	private String compatibilita;
	private Integer consumo;
	private Integer idProdotto;
//	private Integer idPc;
}
