package com.betacom.bb.requests;

import lombok.Data;

@Data
public class CpuReq {
	private String descrizione;
	private String marca;
	private String compatibilita;
	private Integer consumo;
}
