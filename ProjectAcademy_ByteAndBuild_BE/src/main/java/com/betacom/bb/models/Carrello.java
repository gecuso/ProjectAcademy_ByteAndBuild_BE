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
@Table(name = "carrello")
public class Carrello {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	
	@Column(name = "numero_prodotti",
			nullable = false)
	private Integer numeroProdotti;
	
	@Column(name = "prezzo_totale",
			nullable = false)
	private Integer prezzoTotale;
	
	////////////////////////////////	
	
	@OneToOne
	@JoinColumn(name="id_utente",
			    referencedColumnName = "id")	
	private Utente utente;
	
	@OneToMany(mappedBy = "carrello",
			   fetch = FetchType.EAGER)
	private  List<OggettoNelCarrello> oggettoNelCarrello; 	
	
}
