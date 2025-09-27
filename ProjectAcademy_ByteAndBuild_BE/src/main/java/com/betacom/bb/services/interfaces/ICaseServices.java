package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.CaseDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.CaseReq;
import com.betacom.bb.requests.GeneralReq;

public interface ICaseServices {

	void create(CaseReq req) throws AcademyException;
	void createCaseProd(GeneralReq req) throws AcademyException;
	void updateCaseProd(GeneralReq req) throws AcademyException;
	void update(CaseReq req) throws AcademyException;
	void delete(CaseReq req) throws AcademyException;
	CaseDTO findByIdProd(Integer idProd) throws AcademyException;

	CaseDTO getById(Integer id) throws AcademyException;
	List<CaseDTO> listAll();
}
