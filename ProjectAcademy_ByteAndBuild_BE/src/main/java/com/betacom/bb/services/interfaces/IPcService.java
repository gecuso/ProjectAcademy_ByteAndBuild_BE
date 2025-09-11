package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.PcDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.PcReq;

public interface IPcService {
	void create(PcReq pcReq) throws AcademyException;
	
	List<PcDTO> listAll() throws AcademyException;
	Boolean controlloAlimentazione(PcReq pcReq) throws AcademyException;
	Boolean controlloFormato(String form1,String form2);
	Boolean controlloCompatibilita(String comp1,String comp2);
	Boolean controlloQuantita(Integer n,PcReq pcReq) throws AcademyException;
	void riduciQuantita(Integer n,PcReq pcReq);
	
}
