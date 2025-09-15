package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.bb.models.SistemaRaffreddamento;

public interface ISistemaRaffreddamentoRepository extends JpaRepository<SistemaRaffreddamento, Integer>{

	Optional<SistemaRaffreddamento> findByDescrizione(String descrizione);
	
}
