package com.betacom.bb.models;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "id_schedaMadre")
	private SchedaMadre schedaMadre;
	
	@ManyToOne
	@JoinColumn(name = "id_schedaGrafica")
	private  SchedaGrafica schedaGrafica;
	
	@ManyToOne
	@JoinColumn(name = "id_cpu")
	private  Cpu cpu;
	
	@ManyToOne
	@JoinColumn(name = "id_ram")
	private  Ram ram;
	
	@ManyToOne
	@JoinColumn(name = "id_memoria")
	private  Memoria memoria;
	
	@ManyToOne
	@JoinColumn(name = "id_casee")
	private  Case casee;
	
	@ManyToOne
	@JoinColumn(name = "id_sistemaRaffreddamento")
	private  SistemaRaffreddamento sistemaRaffreddamento;
	
	@ManyToOne
	@JoinColumn(name = "id_alimentazione")
	private  Alimentazione alimentazione;
}
