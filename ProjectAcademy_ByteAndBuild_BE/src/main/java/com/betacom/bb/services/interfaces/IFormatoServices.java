package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.FormatoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.FormatoReq;

public interface IFormatoServices {

	void create(FormatoReq req) throws AcademyException;
	void delete(FormatoReq req) throws AcademyException;
	
	FormatoDTO getById(Integer id) throws AcademyException;
	List<FormatoDTO> listAll();
}
