package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.SchedaMadre;

public interface ISchedaMadreRepository extends JpaRepository<SchedaMadre, Integer>{

//	Optional<SchedaMadre> findByDescrizione(String descrizione);
	
}
