package com.betacom.bb.requests;

import lombok.Data;

@Data
public class LaptopReq {
	private Integer id;
	private String descrizione;
	private String caratteristiche;
	private Integer consumo;
	private Integer idProdotto;

}
