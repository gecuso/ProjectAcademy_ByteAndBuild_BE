package com.betacom.bb.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class UtenteReq {
	private Integer id;
	
	private String userName;
	private String pwd;
	private String currentpwd;
	private String email;
	private String indirizzo;
	private String telefono;
	private String role;

}

