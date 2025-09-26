package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Laptop;

public interface ILaptopRepository extends JpaRepository<Laptop, Integer>{

	Optional<Laptop> findByDescrizione(String descrizione);

	@Query("select m from laptop m join m.prodotto p where p.id = :idProdotto")
	Laptop findByIdProd(@Param("idProdotto") Integer idProdotto);
}
