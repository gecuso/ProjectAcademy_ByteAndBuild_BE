package com.betacom.bb.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.OggettoNelCarrello;

public interface IOggettoNelCarrelloRepository extends JpaRepository<OggettoNelCarrello, Integer>{
	
	Optional<List<OggettoNelCarrello>> findByCarrelloId(Integer carrelloId);
	
}
