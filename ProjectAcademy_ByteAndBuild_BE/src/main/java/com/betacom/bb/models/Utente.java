package com.betacom.bb.models;

import com.betacom.bb.utilis.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="utente")
@Getter
@Setter
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private String userName;
	private String pwd;
	private String currentpwd;
	private String email;
	private String indirizzo;
	private String telefono;
	private Roles role;
	
	////////////////////////////////	
	
	@OneToOne(
			mappedBy = "utente",
			cascade = CascadeType.REMOVE
			)
	private Carrello carrello;
	
	
}