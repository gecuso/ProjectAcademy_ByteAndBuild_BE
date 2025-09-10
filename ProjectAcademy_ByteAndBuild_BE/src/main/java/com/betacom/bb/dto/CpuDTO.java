package com.betacom.bb.dto;

import lombok.Data;

@Data
public class CpuDTO {

    private Integer id;
    private String descrizione;
    private String compatibilita;
    private Integer consumo;

}