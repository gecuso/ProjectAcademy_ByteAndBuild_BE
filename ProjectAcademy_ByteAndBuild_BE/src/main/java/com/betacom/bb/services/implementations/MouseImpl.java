package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.MouseDTO;
import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Mouse;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.IMouseRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.MouseReq;
import com.betacom.bb.services.interfaces.IMouseService;
import com.betacom.bb.services.interfaces.IProdottoServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MouseImpl implements IMouseService{

	private IMouseRepository mouseR;
	private IProdottoRepository prodR;
	private IProdottoServices prodS;
	
	
	public MouseImpl(IMouseRepository mouseR, IProdottoRepository prodR, IProdottoServices prodS) {
		super();
		this.mouseR = mouseR;
		this.prodR = prodR;
		this.prodS = prodS;
	}

	////////////////////////////////
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(MouseReq req) throws AcademyException {
		log.debug("create: " + req);
		Optional<Mouse> mou = mouseR.findByDescrizione(req.getDescrizione());
		if(mou.isPresent())
			throw new AcademyException("Mouse già presente nel database");
		
		//controllo dei dati
		Mouse mouse = new Mouse();
		if(req.getDescrizione() == null)
			throw new AcademyException("Descrizione non presente, riprovare");
		mouse.setDescrizione(req.getDescrizione());
		if(req.getCollegamento() == null)
			throw new AcademyException("Collegamento non presente, riprovare");
		mouse.setCollegamento(req.getCollegamento());
		
		if(req.getIdProdotto() == null)
			throw new AcademyException("Id del prodotto non inserito, riprovare");
		Optional<Prodotto> p = prodR.findById(req.getIdProdotto());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non presente nel database");
		mouse.setProdotto(p.get());
		
		//salvo nel database
		mouseR.save(mouse);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createMouseProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		Integer idprod = prodS.create(req.getProdReq());
		
		req.getMouseReq().setDescrizione(req.getProdReq().getDescrizione());
		req.getMouseReq().setIdProdotto(idprod);
		
		create(req.getMouseReq());
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(MouseReq req) throws AcademyException {
		log.debug("create: " + req);
		Optional<Mouse> mou = mouseR.findById(req.getId());
		if(mou.isEmpty())
			throw new AcademyException("Mouse non presente nel database");
		
		//controllo dei dati
		Mouse mouse = new Mouse();
		mouse.setId(mou.get().getId());
		//descrizione non può cambiare
		mouse.setDescrizione(mou.get().getDescrizione());
		if(req.getCollegamento() == null)
			throw new AcademyException("Collegamento non presente, riprovare");
		mouse.setCollegamento(req.getCollegamento());
		//id prodotto non deve cambiare
		mouse.setProdotto(mou.get().getProdotto());
		
		//update nel database
		mouseR.save(mouse);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(MouseReq req) throws AcademyException {
		log.debug("create: " + req);
		Optional<Mouse> mou = mouseR.findById(req.getId());
		if(mou.isEmpty())
			throw new AcademyException("Mouse non presente nel database");
		
		//elimino nel database
		mouseR.delete(mou.get());	
	}

	////////////////////////////////
	
	@Override
	public List<MouseDTO> findAll() throws AcademyException {
		log.debug("findAll mouse");
		List<Mouse> listMouse = mouseR.findAll();
		
		return listMouse.stream()
				.map(mou -> MouseDTO.builder()
						.id(mou.getId())
						.descrizione(mou.getDescrizione())
						.collegamento(mou.getCollegamento())
						.prodotto(ProdottoDTO.builder()
								.id(mou.getProdotto().getId())
								.build())
						.build()).collect(Collectors.toList());
	}
	
	
	@Override
	public MouseDTO getById(Integer id) throws AcademyException {
		log.debug("get Mouse by Id: " + id);
		
		//controllo se il mouse esiste
		Optional<Mouse> m = mouseR.findById(id);
		if(m.isEmpty())
			throw new AcademyException("Mouse non presente nel database");
		
		Mouse mou = m.get();
		return MouseDTO.builder()
				.id(mou.getId())
				.descrizione(mou.getDescrizione())
				.collegamento(mou.getCollegamento())
				.prodotto(ProdottoDTO.builder()
						.id(mou.getProdotto().getId())
						.build())
				.build();		
	}

	@Override
	public MouseDTO findByIdProd(Integer idProd) throws AcademyException {
		Mouse mou = mouseR.findByIdProd(idProd);
		return MouseDTO.builder()
				.id(mou.getId())
				.descrizione(mou.getDescrizione())
				.collegamento(mou.getCollegamento())
				.prodotto(ProdottoDTO.builder()
						.id(mou.getProdotto().getId())
						.build())
				.build();
	}


	
}
