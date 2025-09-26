package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.SchedaMadreDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.SchedaMadreReq;

public interface ISchedaMadreServices {

	void create(SchedaMadreReq req) throws AcademyException;
	void update(SchedaMadreReq req) throws AcademyException;
	void delete(SchedaMadreReq req) throws AcademyException;
	void createSchMdrProd(GeneralReq req) throws AcademyException;
	SchedaMadreDTO findByIdProd(Integer idProd) throws AcademyException;
	
	SchedaMadreDTO getById(Integer id) throws AcademyException;
	List<SchedaMadreDTO> listAll();
}
