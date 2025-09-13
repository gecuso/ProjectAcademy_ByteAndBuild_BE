package com.betacom.bb.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Marca;

public interface IMarcaRepository extends JpaRepository<Marca, Integer>{

	Optional<Marca> findByDescrizione(String descrizione);
	Optional<List<Marca>> findAllByDescrizione(String descrizione);
	
}
