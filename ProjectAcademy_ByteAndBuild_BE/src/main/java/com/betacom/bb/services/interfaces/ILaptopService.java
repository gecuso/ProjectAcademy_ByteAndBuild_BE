package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.LaptopDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.LaptopReq;

public interface ILaptopService {

	void create(LaptopReq req) throws AcademyException;
	void update(LaptopReq req) throws AcademyException;
	void delete(LaptopReq req) throws AcademyException;
	void createLaptopProd(GeneralReq req) throws AcademyException;
	LaptopDTO findByIdProd(Integer idProd) throws AcademyException;

	List<LaptopDTO> findAll() throws AcademyException;
	LaptopDTO getById(Integer id) throws AcademyException;
	
}
