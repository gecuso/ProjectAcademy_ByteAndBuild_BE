package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Prodotto;

public interface IProdottoRepository extends JpaRepository<Prodotto,Integer>{

	Optional<Prodotto> findByDescrizione(String descrizione);
}
