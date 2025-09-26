package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Ram;

public interface IRamRepository extends JpaRepository<Ram, Integer> {
	
	Optional<Ram> findByDescrizione(String descrizione);
	@Query("select m from Ram m join m.prodotto p where p.id = :idProdotto")
	Ram findByIdProd(@Param("idProdotto") Integer idProdotto);
}
