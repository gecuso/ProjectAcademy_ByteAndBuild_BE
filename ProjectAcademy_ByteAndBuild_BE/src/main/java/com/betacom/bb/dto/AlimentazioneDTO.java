package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AlimentazioneDTO {

    private Integer id;
//    private String descrizione;
    private Integer potenza;
    private ProdottoDTO prodotto;

}