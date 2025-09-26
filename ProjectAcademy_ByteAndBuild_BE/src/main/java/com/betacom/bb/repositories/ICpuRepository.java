package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Cpu;

public interface ICpuRepository extends JpaRepository<Cpu, Integer> {

	Optional<Cpu> findByDescrizione(String descrizione);

	@Query("select m from Cpu m join m.prodotto p where p.id = :idProdotto")
    Cpu findByIdProd(@Param("idProdotto") Integer idProdotto);
}
