package com.betacom.bb.services.interfaces;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.MemoriaReq;

public interface IMemoriaService {

	void create(MemoriaReq req) throws AcademyException;
	void update(MemoriaReq req) throws AcademyException;
	void delete(MemoriaReq req) throws AcademyException;
	
}
