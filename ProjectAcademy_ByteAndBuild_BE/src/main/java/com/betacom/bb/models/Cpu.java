package com.betacom.bb.models;

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
@Table(name = "cpu")
public class Cpu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100,
			name = "descrizione",
			nullable = false)
	private String descrizione;
	
	@Column(length = 100,
			name = "marca",
			nullable = false)
	private String marca;
	
	@Column(length = 100,
			name = "compatibilita",
			nullable = false)
	private String compatibilita;
	
	@Column(name = "consumo",
			nullable = false)
	private Integer consumo;
	
	////////////////////////////////

	@OneToOne
	@JoinColumn(name="id_prodotto",
			    referencedColumnName = "id")
	private Prodotto prodotto;
	
	@ManyToOne
	@JoinColumn(name = "id_pc")
	private Pc pc;
	
}
