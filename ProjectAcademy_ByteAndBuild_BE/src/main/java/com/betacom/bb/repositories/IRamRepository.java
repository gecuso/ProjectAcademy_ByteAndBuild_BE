package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Ram;

public interface IRamRepository extends JpaRepository<Ram, Integer> {
	
//	Optional<Ram> findByDescrizione(String descrizione);
	
}
