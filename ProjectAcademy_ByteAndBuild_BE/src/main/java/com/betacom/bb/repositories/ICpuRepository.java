package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Cpu;

public interface ICpuRepository extends JpaRepository<Cpu, Integer> {

	Optional<Cpu> findByDescrizione(String descrizione);
}
