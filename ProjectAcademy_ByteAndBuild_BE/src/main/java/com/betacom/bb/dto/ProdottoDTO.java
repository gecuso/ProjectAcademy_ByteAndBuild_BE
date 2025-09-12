package com.betacom.bb.dto;

import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Marca;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdottoDTO {

	private Integer id;
	private String descrizione;
	private Integer costo;
	private Integer prezzo;
	private Integer quantita;
	private String img;
	private Categoria categoria;
	private Marca marca; 
	
}
