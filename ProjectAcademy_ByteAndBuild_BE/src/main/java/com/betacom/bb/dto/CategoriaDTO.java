package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoriaDTO {

	private Integer id;
    private String descrizione;
    
}
