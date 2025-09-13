package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Laptop;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{

	Optional<Categoria> findByDescrizione(String descrizione);

}
