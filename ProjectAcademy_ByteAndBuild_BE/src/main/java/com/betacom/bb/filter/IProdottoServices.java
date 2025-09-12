package com.betacom.bb.filter;

import java.util.List;

import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.CaseReq;

public interface IProdottoServices {

	void create(CaseReq req) throws AcademyException;
	void delete(CaseReq req) throws AcademyException;
	List<ProdottoDTO> listAll();
	List<ProdottoDTO> list(String descrizione, Integer prezzo, Integer prezzo2);
	
}
