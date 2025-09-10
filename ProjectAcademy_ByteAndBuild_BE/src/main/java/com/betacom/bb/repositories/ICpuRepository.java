package com.betacom.bb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Cpu;

public interface ICpuRepository extends JpaRepository<Cpu, Integer> {

}
