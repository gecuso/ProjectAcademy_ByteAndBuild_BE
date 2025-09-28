package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Alimentazione;
import com.betacom.bb.models.Pc;

public interface IPcRepository extends JpaRepository<Pc, Integer>{
	Optional<Pc> findByDescrizione(String descrizione);
	
	@Query("select m from Pc m join m.prodotto p where p.id = :idProdotto")
	Pc findByIdProd(@Param("idProdotto") Integer idProdotto);
}
