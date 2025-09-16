package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.CaseDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.CaseReq;

public interface ICaseServices {

	void create(CaseReq req) throws AcademyException;
	//void update(CaseReq req) throws AcademyException;
	void delete(CaseReq req) throws AcademyException;
	
	CaseDTO getById(Integer id) throws AcademyException;
	List<CaseDTO> listAll();
	
	List<String> listaDescrizioniCases() throws AcademyException;
	
}
