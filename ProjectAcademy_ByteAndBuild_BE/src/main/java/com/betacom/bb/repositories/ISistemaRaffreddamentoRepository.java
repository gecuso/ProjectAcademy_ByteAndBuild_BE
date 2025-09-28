package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.SistemaRaffreddamento;

public interface ISistemaRaffreddamentoRepository extends JpaRepository<SistemaRaffreddamento, Integer>{

	Optional<SistemaRaffreddamento> findByDescrizione(String descrizione);
	@Query("select m from SistemaRaffreddamento m join m.prodotto p where p.id = :idProdotto")
	SistemaRaffreddamento findByIdProd(@Param("idProdotto") Integer idProdotto);
}
