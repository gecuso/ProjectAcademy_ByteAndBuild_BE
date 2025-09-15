package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Case;

public interface ICaseRepository extends JpaRepository<Case, Integer>{

	Optional<Case> findByDescrizione(String descrizione);
	
}
