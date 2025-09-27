package com.betacom.bb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Carrello;

public interface ICarrelloRepository extends JpaRepository<Carrello, Integer>{
	
}
