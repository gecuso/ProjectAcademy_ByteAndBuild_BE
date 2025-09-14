package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Formato;

public interface IFormatoRepository extends JpaRepository<Formato, Integer>{

	Optional<Formato> findByDescrizione(String descrizione);
}
