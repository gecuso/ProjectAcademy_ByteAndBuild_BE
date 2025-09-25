package com.betacom.bb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "oggetto_nel_carrello")
public class OggettoNelCarrello {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@Column(name = "quantita",
			nullable = false)
	private Integer quantita;	
	
	////////////////////////////////

	@ManyToOne
	@JoinColumn(name = "id_carrello")
	private Carrello carrello;	
	
	@ManyToOne
	@JoinColumn(name = "id_prodotto")
	private Prodotto prodotto;	
		
}
