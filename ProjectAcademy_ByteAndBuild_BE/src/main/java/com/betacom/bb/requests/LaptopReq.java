package com.betacom.bb.requests;

import com.betacom.bb.models.Prodotto;

import lombok.Data;

@Data
public class LaptopReq {
	private Integer id;
//	private String descrizione;
	private String caratteristiche;
	private Integer consumo;
	private Prodotto prodotto;

}
