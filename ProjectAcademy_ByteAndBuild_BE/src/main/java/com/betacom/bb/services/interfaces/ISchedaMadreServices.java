package com.betacom.bb.services.interfaces;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.SchedaMadreReq;

public interface ISchedaMadreServices {

	void create(SchedaMadreReq req) throws AcademyException;
	void update(SchedaMadreReq req) throws AcademyException;
	void delete(SchedaMadreReq req) throws AcademyException;
	
}
