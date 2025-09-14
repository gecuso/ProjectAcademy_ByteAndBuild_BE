package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CpuDTO {

    private Integer id;
//    private String descrizione;
    private String compatibilita;
    private Integer consumo;
    private ProdottoDTO prodotto;

}