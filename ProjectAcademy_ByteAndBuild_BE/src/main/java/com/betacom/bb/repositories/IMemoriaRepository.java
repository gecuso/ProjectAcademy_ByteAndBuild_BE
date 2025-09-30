package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Memoria;

public interface IMemoriaRepository extends JpaRepository<Memoria, Integer>{

	Optional<Memoria> findByDescrizione(String descrizione);
	@Query("select m from Memoria m join m.prodotto p where p.id = :idProdotto")
	Memoria findByIdProd(@Param("idProdotto") Integer idProdotto);
}
