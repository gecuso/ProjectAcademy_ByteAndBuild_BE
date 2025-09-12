package com.betacom.bb.services.interfaces;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.SchedaGraficaReq;

public interface ISchedaGraficaServices {

	void create(SchedaGraficaReq req) throws AcademyException;
	void update(SchedaGraficaReq req) throws AcademyException;
	void delete(SchedaGraficaReq req) throws AcademyException;
	
}
