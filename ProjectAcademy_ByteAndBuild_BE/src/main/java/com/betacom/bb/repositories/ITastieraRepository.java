package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Tastiera;

public interface ITastieraRepository extends JpaRepository<Tastiera, Integer>{
	Optional<Tastiera> findByDescrizione(String descrizione);
	@Query("select m from Tastiera m join m.prodotto p where p.id = :idProdotto")
	Tastiera findByIdProd(@Param("idProdotto") Integer idProdotto);
}
