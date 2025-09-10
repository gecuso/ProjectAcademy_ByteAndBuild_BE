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
@Table(name = "pc")
public class Pc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100,
			name = "descrizione",
			nullable = false)
	private String descrizione;
	
	@Column(length = 100,
			name = "totConsumo",
			nullable = false)
	private Integer totConsumo;

	////////////////////////////////
	
	@OneToOne
	@JoinColumn(name="id_prodotto",
			    referencedColumnName = "id")
	private Prodotto prodotto;
	
	@OneToMany(mappedBy = "pc",
			   fetch = FetchType.EAGER)
	private  List<SchedaMadre> schedaMadre;
	
	@OneToMany(mappedBy = "pc",
			   fetch = FetchType.EAGER)
	private  List<SchedaGrafica> schedaGrafica;
	
	@OneToMany(mappedBy = "pc",
			   fetch = FetchType.EAGER)
	private  List<Cpu> cpu;
	
	@OneToMany(mappedBy = "pc",
			   fetch = FetchType.EAGER)
	private  List<Ram> ram;
	
	@OneToMany(mappedBy = "pc",
			   fetch = FetchType.EAGER)
	private  List<Memoria> memoria;
	
	@OneToMany(mappedBy = "pc",
			   fetch = FetchType.EAGER)
	private  List<Case> casee;
	
	@OneToMany(mappedBy = "pc",
			   fetch = FetchType.EAGER)
	private  List<SistemaRaffreddamento> sistemaRaffreddamento;
	
	@OneToMany(mappedBy = "pc",
			   fetch = FetchType.EAGER)
	private  List<Alimentazione> alimentazione;
}
