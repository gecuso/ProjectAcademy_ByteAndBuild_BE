package com.betacom.bb.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.dto.MarcaDTO;
import com.betacom.bb.models.Marca;

public interface IMarcaRepository extends JpaRepository<Marca, Integer>{

	Optional<Marca> findByDescrizione(String descrizione);
	Optional<List<Marca>> findAllByDescrizione(String descrizione);
	
	@Query("select m from Marca m join m.categoria c where c.id = :idCategoria")
    List<Marca> findByIdCategoria(@Param("idCategoria") Integer idCategoria);
}
