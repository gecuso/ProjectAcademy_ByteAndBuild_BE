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
@Table(name = "ram")
public class Ram {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100,
			name = "descrizione",
			nullable = false)
	private String descrizione;
	
	@Column(name = "consumo",
			nullable = false)
	private Integer consumo;
	
	////////////////////////////////	
	
	@OneToOne
	@JoinColumn(name="id_prodotto",
			    referencedColumnName = "id")
	private Prodotto prodotto;
	
	@OneToMany(mappedBy = "ram",
			   fetch = FetchType.EAGER)
	private  List<Pc> pc;
	
}
