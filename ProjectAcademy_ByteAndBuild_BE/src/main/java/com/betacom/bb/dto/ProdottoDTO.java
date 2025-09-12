package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProdottoDTO {

	private Integer id;
    private String descrizione;
    private Integer costo;
    private Integer prezzo;
    private Integer quantita;
    private String img;
    
    private CategoriaDTO categoria;
	private MarcaDTO marca;
	
}
