package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.ProdottoReq;

public interface IProdottoServices {

	Integer create(ProdottoReq req) throws AcademyException;
	void update(ProdottoReq req) throws AcademyException;
	void delete(Integer idProd) throws AcademyException;

	ProdottoDTO getById(Integer id) throws AcademyException;

	List<ProdottoDTO> listAll();
	
	List<ProdottoDTO> listAllByIdCategoria(Integer id);
	List<ProdottoDTO> list(String descrizione);
	

}
