package com.betacom.bb.services.interfaces;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.LaptopReq;

public interface ILaptopService {

	void create(LaptopReq req) throws AcademyException;
	void update(LaptopReq req) throws AcademyException;
	void delete(LaptopReq req) throws AcademyException;
	
}
