package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.CpuDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.CpuReq;
import com.betacom.bb.requests.GeneralReq;

public interface ICpuServices{

	void create(CpuReq req) throws AcademyException;
	void update(CpuReq req) throws AcademyException;
	void delete(CpuReq req) throws AcademyException;
	void createCpuProd(GeneralReq req)throws AcademyException;
	CpuDTO findByIdProd(Integer idProd) throws AcademyException;
	CpuDTO getById(Integer id) throws AcademyException;
	List<CpuDTO> listAll();
}
