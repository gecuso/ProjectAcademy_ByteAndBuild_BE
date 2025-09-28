package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Monitor;

public interface IMonitorRepository extends JpaRepository<Monitor, Integer>{

	Optional<Monitor> findByDescrizione(String descrizione);
	@Query("select m from Monitor m join m.prodotto p where p.id = :idProdotto")
	Monitor findByIdProd(@Param("idProdotto") Integer idProdotto);
}
