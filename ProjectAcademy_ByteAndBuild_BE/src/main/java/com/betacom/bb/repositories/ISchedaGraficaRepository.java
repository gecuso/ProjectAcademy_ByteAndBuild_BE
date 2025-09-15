package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.SchedaGrafica;

public interface ISchedaGraficaRepository extends JpaRepository<SchedaGrafica, Integer>{

//	Optional<SchedaGrafica> findByDescrizione(String descrizione);
	
}
