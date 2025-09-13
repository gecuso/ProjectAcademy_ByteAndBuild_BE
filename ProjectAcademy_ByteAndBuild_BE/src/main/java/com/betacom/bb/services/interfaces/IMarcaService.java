package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.MarcaDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.MarcaReq;

public interface IMarcaService {

	void create(MarcaReq req) throws AcademyException;
	void update(MarcaReq req) throws AcademyException;
	void delete(MarcaReq req) throws AcademyException;
	
	List<MarcaDTO> findAll() throws AcademyException;
	MarcaDTO getById(Integer id) throws AcademyException;
	
	//questo metodo non avrà duplicati
	List<String> findAllMarche() throws AcademyException;
	
}
