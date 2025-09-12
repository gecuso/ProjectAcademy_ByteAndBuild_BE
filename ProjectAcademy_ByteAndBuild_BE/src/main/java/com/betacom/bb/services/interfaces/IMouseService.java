package com.betacom.bb.services.interfaces;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.MouseReq;

public interface IMouseService {

	void create(MouseReq req) throws AcademyException;
	void update(MouseReq req) throws AcademyException;
	void delete(MouseReq req) throws AcademyException;
	
}
