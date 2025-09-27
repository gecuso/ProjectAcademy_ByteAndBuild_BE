package com.betacom.bb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UtenteDTO {
	private Integer id;
	
	private String userName;
	private String pwd;
	private String currentpwd;
	private String email;
	private String indirizzo;
	private String telefono;
	private String role;

}
