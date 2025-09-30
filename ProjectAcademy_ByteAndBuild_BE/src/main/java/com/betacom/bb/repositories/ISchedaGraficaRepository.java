package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.SchedaGrafica;

public interface ISchedaGraficaRepository extends JpaRepository<SchedaGrafica, Integer>{

	Optional<SchedaGrafica> findByDescrizione(String descrizione);
	@Query("select m from SchedaGrafica m join m.prodotto p where p.id = :idProdotto")
	SchedaGrafica findByIdProd(@Param("idProdotto") Integer idProdotto);
}
