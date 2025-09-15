package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Tastiera;

public interface ITastieraRepository extends JpaRepository<Tastiera, Integer>{
//	Optional<Tastiera> findByDescrizione(String descrizione);
}
