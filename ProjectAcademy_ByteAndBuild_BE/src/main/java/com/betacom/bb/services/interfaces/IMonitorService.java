package com.betacom.bb.services.interfaces;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.MonitorReq;

public interface IMonitorService {

	void create(MonitorReq req) throws AcademyException;
	void update(MonitorReq req) throws AcademyException;
	void delete(MonitorReq req) throws AcademyException;
	
}
