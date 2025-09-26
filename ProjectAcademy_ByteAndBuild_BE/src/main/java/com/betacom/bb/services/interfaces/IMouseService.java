package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.MouseDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.MouseReq;

public interface IMouseService {

	void create(MouseReq req) throws AcademyException;
	void update(MouseReq req) throws AcademyException;
	void delete(MouseReq req) throws AcademyException;
	void createMouseProd(GeneralReq req) throws AcademyException;
	MouseDTO findByIdProd(Integer idProd) throws AcademyException;
	
	List<MouseDTO> findAll() throws AcademyException;
	MouseDTO getById(Integer id) throws AcademyException;
	
}
