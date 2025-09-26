package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Alimentazione;
import com.betacom.bb.models.Case;

public interface ICaseRepository extends JpaRepository<Case, Integer>{

	Optional<Case> findByDescrizione(String descrizione);
	
	@Query("select m from casee m join m.prodotto p where p.id = :idProdotto")
    Case findByIdProd(@Param("idProdotto") Integer idProdotto);
}
