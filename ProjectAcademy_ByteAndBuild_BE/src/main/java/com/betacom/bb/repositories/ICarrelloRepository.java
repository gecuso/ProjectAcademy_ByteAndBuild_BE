package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Carrello;

public interface ICarrelloRepository extends JpaRepository<Carrello, Integer>{

	Optional<Carrello> findByIdUtente(Integer id);
	
}
