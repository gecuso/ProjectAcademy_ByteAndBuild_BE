package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Mouse;

public interface IMouseRepository extends JpaRepository<Mouse, Integer>{

	Optional<Mouse> findByDescrizione(String descrizione);
	@Query("select m from Mouse m join m.prodotto p where p.id = :idProdotto")
	Mouse findByIdProd(@Param("idProdotto") Integer idProdotto);
}
