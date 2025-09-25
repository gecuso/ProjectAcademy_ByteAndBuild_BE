package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.MemoriaDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.MemoriaReq;

public interface IMemoriaService {

	void create(MemoriaReq req) throws AcademyException;
	void update(MemoriaReq req) throws AcademyException;
	void delete(MemoriaReq req) throws AcademyException;
	void createMemProd(GeneralReq req) throws AcademyException;

	List<MemoriaDTO> findAll() throws AcademyException;
	MemoriaDTO getById(Integer id) throws AcademyException;
	
}
