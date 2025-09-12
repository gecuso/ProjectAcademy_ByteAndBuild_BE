package com.betacom.bb.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "memoria")
public class Memoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100,
			name = "descrizione",
			nullable = false,
			unique = true)
	private String descrizione;
	
	@Column(name = "spazio",
			nullable = false)
	private Integer spazio;
	
	////////////////////////////////
	
	@OneToOne
	@JoinColumn(name="id_prodotto",
			    referencedColumnName = "id")
	private Prodotto prodotto;

	@OneToMany(mappedBy = "memoria",
			   fetch = FetchType.EAGER)
	private List<Pc> pc;
	
}
