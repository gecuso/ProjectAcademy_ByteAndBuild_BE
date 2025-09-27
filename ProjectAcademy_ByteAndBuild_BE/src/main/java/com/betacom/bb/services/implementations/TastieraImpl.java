package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.TastieraDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.models.Tastiera;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.repositories.ITastieraRepository;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.TastieraReq;
import com.betacom.bb.services.interfaces.IProdottoServices;
import com.betacom.bb.services.interfaces.ITastieraService;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TastieraImpl extends Utilities implements ITastieraService{

	private ITastieraRepository tastR;
	private IProdottoRepository prodR;
	private IProdottoServices prodS;

	public TastieraImpl(ITastieraRepository tastR, IProdottoRepository prodR, IProdottoServices prodS) {
		super();
		this.tastR = tastR;
		this.prodR = prodR;
		this.prodS = prodS;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(TastieraReq req) throws AcademyException {
		log.debug("create: " + req);
		Optional<Tastiera> tasO = tastR.findByDescrizione(req.getDescrizione());
		if(tasO.isPresent())
			throw new AcademyException("tastiera già presente nel database");
		
		//controllo dei dati
		Tastiera tastiera = new Tastiera();
		if(req.getDescrizione()==null)
			throw new AcademyException("Descrizione non presente, riprovare");
		tastiera.setDescrizione(req.getDescrizione());
		if(req.getTipologia()==null)
			throw new AcademyException("tipologia non presente, riprovare");
		tastiera.setTipologia(req.getTipologia());
		if(req.getCollegamento()==null)
			throw new AcademyException("collegamento non presente, riprovare");
		tastiera.setCollegamento(req.getCollegamento());
		
		if(req.getIdProdotto() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		Optional<Prodotto> p = prodR.findById(req.getIdProdotto());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non presente nel database");
		tastiera.setProdotto(p.get());
		
		//salvo nel database
		tastR.save(tastiera);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createTastProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		Integer idprod = prodS.create(req.getProdReq());
		
		req.getTastReq().setDescrizione(req.getProdReq().getDescrizione());
		req.getTastReq().setIdProdotto(idprod);
		
		create(req.getTastReq());
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateTastProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		prodS.update(req.getProdReq());
		
		req.getTastReq().setDescrizione(req.getProdReq().getDescrizione());
		
		update(req.getTastReq());
	}
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteTastProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		delete(req.getTastReq());
		prodS.delete(req.getProdReq().getId());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(TastieraReq req) throws AcademyException {
		log.debug("update: " + req);
		Optional<Tastiera> tasO = tastR.findById(req.getId());
		if(tasO.isPresent())
			throw new AcademyException("tastiera già presente nel database");
		
		//controllo dei dati
		Tastiera tastiera = tasO.get();
		if(req.getDescrizione()==null)
			throw new AcademyException("Descrizione non presente, riprovare");
		tastiera.setDescrizione(req.getDescrizione());
		if(req.getTipologia()==null)
			throw new AcademyException("tipologia non presente, riprovare");
		tastiera.setTipologia(req.getTipologia());
		if(req.getCollegamento()==null)
			throw new AcademyException("collegamento non presente, riprovare");
		tastiera.setCollegamento(req.getCollegamento());
		
		if(req.getIdProdotto() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		Optional<Prodotto> p = prodR.findById(req.getIdProdotto());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non presente nel database");
		tastiera.setProdotto(p.get());
		
		//salvo nel database
		tastR.save(tastiera);		
	}

	@Override
	public void delete(TastieraReq req) throws AcademyException {
		log.debug("delete: " + req);
		Optional<Tastiera> mon = tastR.findById(req.getId());
		if(mon.isEmpty())
			throw new AcademyException("tastiera non presente nel database");
		
		//elimino nel database
		tastR.delete(mon.get());
		
	}

	@Override
	public List<TastieraDTO> findAll() throws AcademyException {
		List<Tastiera> lt = tastR.findAll();
		return buildListTastieraDTO(lt);
	}

	@Override
	public TastieraDTO getById(Integer id) throws AcademyException {
		Tastiera m = tastR.getById(id);
		return buildTastieraDTO(m);
	}

	@Override
	public TastieraDTO findByIdProd(Integer idProd) throws AcademyException {
		Tastiera alim = tastR.findByIdProd(idProd);
		return buildTastieraDTO(alim);
	}

}
