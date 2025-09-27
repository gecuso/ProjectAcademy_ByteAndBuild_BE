package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.OggettoNelCarrelloDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.OggettoNelCarrelloReq;

public interface IOggettoNelCarrelloService {

	void create(OggettoNelCarrelloReq req) throws AcademyException;
	void update(OggettoNelCarrelloReq req) throws AcademyException;
	void delete(OggettoNelCarrelloReq req) throws AcademyException;	

	List<OggettoNelCarrelloDTO> findAll();
	OggettoNelCarrelloDTO getById(Integer id) throws AcademyException;	
	List<OggettoNelCarrelloDTO> getByIdCarrello(Integer id) throws AcademyException;
	
}
