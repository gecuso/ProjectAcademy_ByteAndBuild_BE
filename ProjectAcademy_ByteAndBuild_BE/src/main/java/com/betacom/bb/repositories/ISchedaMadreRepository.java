package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Alimentazione;
import com.betacom.bb.models.SchedaMadre;

public interface ISchedaMadreRepository extends JpaRepository<SchedaMadre, Integer>{

	Optional<SchedaMadre> findByDescrizione(String descrizione);
	@Query("select m from SchedaMadre m join m.prodotto p where p.id = :idProdotto")
	SchedaMadre findByIdProd(@Param("idProdotto") Integer idProdotto);

}
