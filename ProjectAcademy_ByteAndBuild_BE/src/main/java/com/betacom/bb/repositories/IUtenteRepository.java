package com.betacom.bb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.bb.models.Utente;


public interface IUtenteRepository extends JpaRepository<Utente, Integer>{
	Optional<Utente> findByUserName(String userName);
	Optional<Utente> findByUserNameAndPwd(String userName, String pwd);
}
