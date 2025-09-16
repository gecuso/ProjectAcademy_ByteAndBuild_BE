package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.SchedaMadreDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.SchedaMadreReq;

public interface ISchedaMadreServices {

	void create(SchedaMadreReq req) throws AcademyException;
	void update(SchedaMadreReq req) throws AcademyException;
	void delete(SchedaMadreReq req) throws AcademyException;
	
	SchedaMadreDTO getById(Integer id) throws AcademyException;
	List<SchedaMadreDTO> listAll();
	
	List<String> listaDescrizioniSchedeMadri() throws AcademyException;
	
}
