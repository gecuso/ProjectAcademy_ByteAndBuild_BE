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
@Table(name = "case")
public class Case {

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
			name = "dimensioni")
	private String dimensioni;
	
	////////////////////////////////	
	
	@ManyToOne
	@JoinColumn(name = "formato_id")
	private Formato formato; //foreign key di formato (ATX, MICROATX, MINI)
	


}
