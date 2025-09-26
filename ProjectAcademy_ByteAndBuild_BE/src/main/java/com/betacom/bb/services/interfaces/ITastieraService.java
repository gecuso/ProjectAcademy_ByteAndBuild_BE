package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.TastieraDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.TastieraReq;

public interface ITastieraService {
	void create(TastieraReq req) throws AcademyException;
	void update(TastieraReq req) throws AcademyException;
	void delete(TastieraReq req) throws AcademyException;
	void createTastProd(GeneralReq req) throws AcademyException;
	TastieraDTO findByIdProd(Integer idProd) throws AcademyException;

	List<TastieraDTO> findAll() throws AcademyException;
	TastieraDTO getById(Integer id) throws AcademyException;
}
