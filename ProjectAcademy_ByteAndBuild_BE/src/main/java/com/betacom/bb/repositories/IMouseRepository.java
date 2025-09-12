package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Mouse;

public interface IMouseRepository extends JpaRepository<Mouse, Integer>{

	Optional<Mouse> findByDescrizione(String descrizione);
	
}
