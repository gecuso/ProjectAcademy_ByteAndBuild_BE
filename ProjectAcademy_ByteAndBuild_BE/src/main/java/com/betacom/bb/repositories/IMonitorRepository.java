package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Monitor;

public interface IMonitorRepository extends JpaRepository<Monitor, Integer>{

	Optional<Monitor> findByDescrizione(String descrizione);
	
}
