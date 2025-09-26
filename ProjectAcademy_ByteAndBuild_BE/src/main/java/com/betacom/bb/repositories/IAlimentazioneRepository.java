package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Alimentazione;

public interface IAlimentazioneRepository extends JpaRepository<Alimentazione, Integer>{

	Optional<Alimentazione> findByDescrizione(String descrizione);
	
	@Query("select m from Alimentazione m join m.prodotto p where p.id = :idProdotto")
    Alimentazione findByIdProd(@Param("idProdotto") Integer idProdotto);
}
