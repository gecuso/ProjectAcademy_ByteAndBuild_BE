package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.CategoriaDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.CategoriaReq;

public interface ICategoriaService {
	void create(CategoriaReq catReq) throws AcademyException;
	void delete(CategoriaReq catReq) throws AcademyException;
	void update(CategoriaReq catReq) throws AcademyException;
	CategoriaDTO getById(Integer id) throws AcademyException;

	List<CategoriaDTO> listall();
}
