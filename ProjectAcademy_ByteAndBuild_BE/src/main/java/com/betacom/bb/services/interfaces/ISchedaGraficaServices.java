package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.SchedaGraficaDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.SchedaGraficaReq;

public interface ISchedaGraficaServices {

	void create(SchedaGraficaReq req) throws AcademyException;
	void update(SchedaGraficaReq req) throws AcademyException;
	void delete(SchedaGraficaReq req) throws AcademyException;
	void createSchGrfProd(GeneralReq req) throws AcademyException;
	SchedaGraficaDTO findByIdProd(Integer idProd) throws AcademyException;

	SchedaGraficaDTO getById(Integer id) throws AcademyException;
	List<SchedaGraficaDTO> listAll();
}
