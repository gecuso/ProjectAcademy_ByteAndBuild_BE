package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.CategoriaDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.CategoriaReq;

public interface ICategoriaServices {
	void create(CategoriaReq req) throws AcademyException;
	void delete(CategoriaReq req) throws AcademyException;
	void update(CategoriaReq req) throws AcademyException;
	
	CategoriaDTO getById(Integer id) throws AcademyException;
	List<CategoriaDTO> listAll();
}
