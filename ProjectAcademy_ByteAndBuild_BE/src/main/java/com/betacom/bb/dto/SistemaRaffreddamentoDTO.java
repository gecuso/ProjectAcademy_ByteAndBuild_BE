package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SistemaRaffreddamentoDTO {

    private Integer id;
//    private String descrizione;
    private Integer consumo;
    private ProdottoDTO prodotto;

}