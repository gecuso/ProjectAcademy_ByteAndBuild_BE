package com.betacom.bb.services.interfaces;

import java.util.List;

import com.betacom.bb.dto.SignInDTO;
import com.betacom.bb.dto.UtenteDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.SignInReq;
import com.betacom.bb.requests.UtenteReq;

public interface IUtenteServices {
	void create(UtenteReq req) throws AcademyException;
	void update(UtenteReq req) throws AcademyException;
	UtenteDTO remove(UtenteReq req) throws AcademyException;
	
		
	List<UtenteDTO> listAll();
	
	UtenteDTO findById(Integer id) throws AcademyException;
	
	SignInDTO signIn(SignInReq req);
}
