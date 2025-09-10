package com.betacom.bb.dto;

import java.util.List;

import lombok.Data;

@Data
public class FormatoDTO {

    private Integer id;
    private String descrizione;
    private List<SchedaMadreDTO> schedaMadre;
    private List<CaseDTO> casee;

}
