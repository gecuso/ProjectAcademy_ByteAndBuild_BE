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
@Table(name = "prodotto")
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100,
			name = "descrizione",
			nullable = false)
	private String descrizione;
	
	@Column(name = "costo",
			nullable = false)
	private Integer costo;
	
	@Column(name = "prezzo",
			nullable = false)
	private Integer prezzo;
	
	@Column(name = "quantita",
			nullable = false)
	private Integer quantita;
	
	@Column(length = 100000,
			name = "img",
			nullable = false)
	private String img;
	
	////////////////////////////////
	
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="id_marca")
	private Marca marca; 
	
}
