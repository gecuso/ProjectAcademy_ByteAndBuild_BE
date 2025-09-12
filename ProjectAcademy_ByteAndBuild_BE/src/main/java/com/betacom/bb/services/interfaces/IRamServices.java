package com.betacom.bb.services.interfaces;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.RamReq;

public interface IRamServices {

	void create(RamReq req) throws AcademyException;
	void update(RamReq req) throws AcademyException;
	void delete(RamReq req) throws AcademyException;
}
