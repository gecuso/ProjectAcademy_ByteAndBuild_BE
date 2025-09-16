package com.betacom.bb.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Prodotto;

public interface IProdottoRepository extends JpaRepository<Prodotto,Integer>{

	Optional<Prodotto> findByDescrizione(String descrizione);
	
	List<Prodotto> findAllCategoriaById(Integer idCategoria);
	
	 @Query("SELECT p FROM Prodotto p WHERE p.categoria.id = :idCategoria")
	 List<Prodotto> findAllByCategoria(@Param("idCategoria") Integer idCategoria);
}
