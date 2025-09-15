package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CaseDTO {

    private Integer id;
    private String descrizione;
    private String dimensioni;
    
    private ProdottoDTO prodotto;
    private FormatoDTO formato;

}