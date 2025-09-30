package com.betacom.bb.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PcReq {

	private Integer id;
	private String descrizione;
	private Integer idProdotto;
	
	private  Integer idAlimentazione;
	private  Integer idCase;
	private  Integer idCpu;
	private  Integer idMemoria;
	private  Integer idRam;
	private  Integer idSchedaGrafica;
	private  Integer idSchedaMadre;
	private  Integer idSistemaRaffreddamento;
	
}
