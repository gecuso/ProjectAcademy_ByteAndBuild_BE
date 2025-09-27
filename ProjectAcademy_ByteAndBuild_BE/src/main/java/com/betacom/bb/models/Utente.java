package com.betacom.bb.models;

import java.util.List;

import com.betacom.bb.utilis.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "utente",
			   fetch = FetchType.EAGER)
	private List<Carrello> carrello;
	
	
}