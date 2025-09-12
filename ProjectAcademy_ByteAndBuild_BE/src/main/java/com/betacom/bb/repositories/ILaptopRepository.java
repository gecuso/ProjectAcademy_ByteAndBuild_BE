package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Laptop;

public interface ILaptopRepository extends JpaRepository<Laptop, Integer>{

	Optional<Laptop> findByDescrizione(String descrizione);
	
}
