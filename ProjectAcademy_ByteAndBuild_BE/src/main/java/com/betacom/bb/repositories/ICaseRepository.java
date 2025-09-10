package com.betacom.bb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Case;

public interface ICaseRepository extends JpaRepository<Case, Integer>{

}
