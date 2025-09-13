package com.betacom.bb.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private Alimentazione alimentazione;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private Case casee;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private Cpu cpu;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private Laptop laptop;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private Memoria memoria;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private Monitor monitor;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private Mouse mouse;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private Pc pc;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private Ram ram;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private SchedaGrafica schedagrafica;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private SchedaMadre schedamadre;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private SistemaRaffreddamento sistemaRaffreddamento;
	
	@OneToOne(
			mappedBy = "prodotto",
			cascade = CascadeType.REMOVE
			)
	private Tastiera tastiera;
	
	
}
