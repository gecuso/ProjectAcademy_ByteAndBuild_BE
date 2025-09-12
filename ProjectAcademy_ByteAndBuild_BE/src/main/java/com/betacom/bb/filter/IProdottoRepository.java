package com.betacom.bb.filter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.bb.models.Prodotto;

public interface IProdottoRepository extends JpaRepository<Prodotto,Integer>{

	@Query(name="prodotto.selectByFilter")
	List<Prodotto> searchByFilter(@Param("descrizione") String descrizione,
			@Param("prezzo") Integer prezzo, 
			@Param("prezzo2") Integer prezzo2);

}
