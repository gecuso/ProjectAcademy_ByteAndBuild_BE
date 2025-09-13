package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.RamDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.RamReq;

public interface IRamServices {

	void create(RamReq req) throws AcademyException;
	void update(RamReq req) throws AcademyException;
	void delete(RamReq req) throws AcademyException;
	
	RamDTO getById(Integer id) throws AcademyException;
	List<RamDTO> listAll();
}
