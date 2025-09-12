package com.betacom.bb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer>{

	List<Categoria> findByMarca(Integer id_marca);
	
}
