package com.betacom.bb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "monitor")
public class Monitor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100,
			name = "descrizione",
			nullable = false)
	private String descrizione;
	
	@Column(length = 100,
			name = "risoluzione",
			nullable = false)
	private String risoluzione;
	
	@Column(length = 100,
			name = "latenza",
			nullable = false)
	private String latenza;
	
	@Column(length = 100,
			name = "frequenza",
			nullable = false)
	private String frequenza;
	
	////////////////////////////////
	
	@OneToOne
	@JoinColumn(name="id_prodotto",
			    referencedColumnName = "id")
	private Prodotto prodotto;
}
