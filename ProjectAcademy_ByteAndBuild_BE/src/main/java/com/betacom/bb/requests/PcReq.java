package com.betacom.bb.requests;

import lombok.Data;

@Data
public class PcReq {

	private Integer id;
	private String descrizione;
	private Integer totConsumo;
	private Integer idProdotto;
	////////////////////////////////
	
	private  Integer idSchedaMadre;
	private  Integer idSchedaGrafica;
	private  Integer idCpu;
	private  Integer idRam;
	private  Integer idMemoria;
	private  Integer idCase;
	private  Integer idSistemaRaffreddamento;
	private  Integer idAlimentazione;
}
