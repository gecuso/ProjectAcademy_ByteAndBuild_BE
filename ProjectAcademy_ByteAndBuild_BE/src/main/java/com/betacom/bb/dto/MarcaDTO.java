package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MarcaDTO {

	private Integer id;
    private String descrizione;
    private CategoriaDTO categoria;
    
}
