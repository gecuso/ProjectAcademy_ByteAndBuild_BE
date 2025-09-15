package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Pc;

public interface IPcRepository extends JpaRepository<Pc, Integer>{
	Optional<Pc> findByDescrizione(String descrizione);
}
