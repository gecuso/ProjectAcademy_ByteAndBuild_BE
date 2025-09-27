package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.CarrelloDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.CarrelloReq;

public interface ICarrelloService {

	void create(CarrelloReq req) throws AcademyException;
	void update(CarrelloReq req) throws AcademyException;
	void delete(CarrelloReq req) throws AcademyException;	
	
	List<CarrelloDTO> findAll();
	CarrelloDTO getById(Integer id) throws AcademyException;
	CarrelloDTO getByIdUtente(Integer id) throws AcademyException;
	
	void svuotaCarrello(Integer id) throws AcademyException;
	
}
