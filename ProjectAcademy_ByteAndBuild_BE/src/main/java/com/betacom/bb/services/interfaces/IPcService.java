package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.PcDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.PcReq;

public interface IPcService {
	Integer create(PcReq pcReq) throws AcademyException;
	void update(PcReq pcReq) throws AcademyException;
	void delete(PcReq pcReq) throws AcademyException;
	
	void createPcProd(GeneralReq req) throws AcademyException;
	void updatePcProd(GeneralReq req) throws AcademyException;
	void deletePcProd(GeneralReq req) throws AcademyException;
	
	PcDTO getById(Integer id) throws AcademyException;
	PcDTO findByIdProd(Integer idProd) throws AcademyException;
	
	List<PcDTO> listAll() throws AcademyException;
	Boolean controlloAlimentazione(PcReq pcReq) throws AcademyException;
	Boolean controlloFormato(String form1,String form2);
	Boolean controlloCompatibilita(String comp1,String comp2);
	Boolean controlloQuantita(Integer n,PcReq pcReq) throws AcademyException;
	void riduciQuantita(Integer n,PcReq pcReq);
	void aumentaQuantita(Integer n, PcReq pc);

	
	
}
