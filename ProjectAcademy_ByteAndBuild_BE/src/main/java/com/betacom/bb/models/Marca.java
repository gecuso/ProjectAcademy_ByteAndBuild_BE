package com.betacom.bb.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "marca")
public class Marca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100,
			name = "descrizione",
			nullable = false)
	private String descrizione;
	
	////////////////////////////////
	
	@OneToMany(mappedBy = "marca",
			   fetch = FetchType.EAGER)
	private List<Prodotto> prodotto;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "categoria_marca",
			   joinColumns = @JoinColumn(name = "id_marca"),
			   inverseJoinColumns = @JoinColumn(name = "id_categoria"))
	List<Categoria> categoria;
}
