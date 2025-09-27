package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.MonitorDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.MonitorReq;

public interface IMonitorService {

	void create(MonitorReq req) throws AcademyException;
	void update(MonitorReq req) throws AcademyException;
	void delete(MonitorReq req) throws AcademyException;
	void createMonitorProd(GeneralReq req) throws AcademyException;
	void updateMonitorProd(GeneralReq req) throws AcademyException;
	void deleteMonitorProd(GeneralReq req) throws AcademyException;

	MonitorDTO findByIdProd(Integer idProd) throws AcademyException;

	
	List<MonitorDTO> findAll() throws AcademyException;
	MonitorDTO getById(Integer id) throws AcademyException;
	
}
