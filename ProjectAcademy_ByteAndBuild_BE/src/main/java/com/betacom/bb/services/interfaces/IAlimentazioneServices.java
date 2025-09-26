package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.AlimentazioneDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.AlimentazioneReq;
import com.betacom.bb.requests.GeneralReq;

public interface IAlimentazioneServices {

	void create(AlimentazioneReq req) throws AcademyException;
	void createAlimProd(GeneralReq req) throws AcademyException;
	void update(AlimentazioneReq req) throws AcademyException;
	void delete(AlimentazioneReq req) throws AcademyException;
	AlimentazioneDTO findByIdProd(Integer idProd) throws AcademyException;
	AlimentazioneDTO getById(Integer id) throws AcademyException;
	List<AlimentazioneDTO> listAll();
	
}
