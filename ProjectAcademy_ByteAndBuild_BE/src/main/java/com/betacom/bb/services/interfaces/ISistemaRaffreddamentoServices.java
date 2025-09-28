package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.SistemaRaffreddamentoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.SistemaRaffreddamentoReq;

public interface ISistemaRaffreddamentoServices {

	void create(SistemaRaffreddamentoReq req) throws AcademyException;
	void update(SistemaRaffreddamentoReq req) throws AcademyException;
	void delete(SistemaRaffreddamentoReq req) throws AcademyException;
	void createSisRafProd(GeneralReq req) throws AcademyException;
	void updateSisRafProd(GeneralReq req) throws AcademyException;
	void deleteSisRafProd(GeneralReq req) throws AcademyException;

	SistemaRaffreddamentoDTO findByIdProd(Integer idProd) throws AcademyException;

	SistemaRaffreddamentoDTO getById(Integer id) throws AcademyException;
	List<SistemaRaffreddamentoDTO> listAll();
}
