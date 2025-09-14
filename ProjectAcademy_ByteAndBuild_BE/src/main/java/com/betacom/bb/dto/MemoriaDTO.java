package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemoriaDTO {

    private Integer id;
//    private String descrizione;
    private Integer spazio;
    private ProdottoDTO prodotto;

}