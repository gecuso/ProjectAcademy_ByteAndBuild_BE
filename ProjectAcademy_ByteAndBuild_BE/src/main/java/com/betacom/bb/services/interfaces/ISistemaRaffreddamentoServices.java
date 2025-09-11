package com.betacom.bb.services.interfaces;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.SistemaRaffreddamentoReq;

public interface ISistemaRaffreddamentoServices {

	void create(SistemaRaffreddamentoReq req) throws AcademyException;
	void update(SistemaRaffreddamentoReq req) throws AcademyException;
	void delete(SistemaRaffreddamentoReq req) throws AcademyException;
}
