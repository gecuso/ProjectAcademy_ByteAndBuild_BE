package com.betacom.bb.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.bb.models.Alimentazione;

public interface IAlimentazioneRepository extends JpaRepository<Alimentazione, Integer>{

	Optional<Alimentazione> findByDescrizione(String descrizione);
	
	
}
