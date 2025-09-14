package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Memoria;

public interface IMemoriaRepository extends JpaRepository<Memoria, Integer>{

//	Optional<Memoria> findByDescrizione(String descrizione);
	
}
