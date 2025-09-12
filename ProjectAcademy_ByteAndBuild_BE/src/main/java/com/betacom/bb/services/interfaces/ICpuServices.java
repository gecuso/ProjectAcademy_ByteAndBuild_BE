package com.betacom.bb.services.interfaces;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.CpuReq;

public interface ICpuServices{

	void create(CpuReq req) throws AcademyException;
	void update(CpuReq req) throws AcademyException;
	void delete(CpuReq req) throws AcademyException;
	
}
