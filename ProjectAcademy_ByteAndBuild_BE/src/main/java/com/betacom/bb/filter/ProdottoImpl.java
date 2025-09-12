package com.betacom.bb.filter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Case;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.ICaseRepository;
import com.betacom.bb.requests.CaseReq;
import com.betacom.bb.services.interfaces.ICaseServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProdottoImpl implements IProdottoServices{

	private IProdottoRepository prodR;

	public ProdottoImpl(IProdottoRepository prodR) {
		this.prodR = prodR;
	}

	@Override
	public void create(CaseReq req) throws AcademyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CaseReq req) throws AcademyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProdottoDTO> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdottoDTO> list(String descrizione, Integer prezzo, Integer prezzo2) {
		log.debug("Metodo filter di PRODOTTO");
		List<Prodotto> lP = prodR.searchByFilter(descrizione,prezzo, prezzo2);
		return null;
	}
	
}
