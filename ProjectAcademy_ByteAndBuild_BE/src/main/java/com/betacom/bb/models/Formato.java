package com.betacom.bb.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "formato")
public class Formato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100,
			name = "descrizione",
			nullable = false)
	private String descrizione;
	
	////////////////////////////////
	
	@OneToMany(mappedBy = "formato",
			   fetch = FetchType.EAGER)
	private List<SchedaMadre> schedaMadre;
	
	@OneToMany(mappedBy = "formato",
			   fetch = FetchType.EAGER)
	private  List<Case> casee; //doppia ee perche case da errore
		
}
