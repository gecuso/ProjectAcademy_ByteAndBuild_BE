package com.betacom.bb.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.MemoriaDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Memoria;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.IMemoriaRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.MemoriaReq;
import com.betacom.bb.services.interfaces.IMemoriaService;
import com.betacom.bb.services.interfaces.IProdottoServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MemoriaImpl extends Utilities implements IMemoriaService{

	private IMemoriaRepository memR;
	private IProdottoRepository prodR;
	private IProdottoServices prodS;

	
	public MemoriaImpl(IMemoriaRepository memR, IProdottoRepository prodR, IProdottoServices prodS) {
		super();
		this.memR = memR;
		this.prodR = prodR;
		this.prodS = prodS;
	}

	////////////////////////////////
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(MemoriaReq req) throws AcademyException {
		log.debug("create: " + req);
		Optional<Memoria> mem = memR.findByDescrizione(req.getDescrizione());
		if(mem.isPresent())
			throw new AcademyException("Memoria già presente nel database");
		
		//controllo dei dati
		Memoria memoria = new Memoria();
		if(req.getDescrizione().isEmpty())
			throw new AcademyException("Descrizione non presente, riprovare");
		memoria.setDescrizione(req.getDescrizione());
		if(req.getSpazio() == null || req.getSpazio()<=0)
			throw new AcademyException("Spazio non presente o non valido, riprovare");
		memoria.setSpazio(req.getSpazio());
		
		if(req.getIdProdotto() == null)
			throw new AcademyException("Id del prodotto non inserito, riprovare");
		
		//controllo se esiste il prodotto
		Optional<Prodotto> prodotto = prodR.findById(req.getIdProdotto());
		if(prodotto.isEmpty())
			throw new AcademyException("Prodotto non presente, riprovare");
		memoria.setProdotto(prodotto.get());
		
		//salvo nel database
		memR.save(memoria);	
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createMemProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		Integer idprod = prodS.create(req.getProdReq());
		
		req.getMemReq().setDescrizione(req.getProdReq().getDescrizione());
		req.getMemReq().setIdProdotto(idprod);
		
		create(req.getMemReq());

	}
	
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(MemoriaReq req) throws AcademyException {
		log.debug("update: " + req);
		Optional<Memoria> mem = memR.findById(req.getId());
		if(mem.isEmpty())
			throw new AcademyException("Memoria non presente nel database");
		
		//controllo dei dati
		Memoria memoria = new Memoria();
		memoria.setId(mem.get().getId());
		//descrizione non può cambiare
		memoria.setDescrizione(mem.get().getDescrizione());
		if(req.getSpazio() == null || req.getSpazio()<=0)
			throw new AcademyException("Spazio non presente o non valido, riprovare");
		memoria.setSpazio(req.getSpazio());
		//id prodotto non deve cambiare
		memoria.setProdotto(mem.get().getProdotto());
		if(!mem.get().getPc().isEmpty())
			throw new AcademyException("Memoria contenuta in un pc; non eliminabile");
		
		//update nel database
		memR.save(memoria);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(MemoriaReq req) throws AcademyException {
		log.debug("delete: " + req);
		Optional<Memoria> mem = memR.findById(req.getId());
		if(mem.isEmpty())
			throw new AcademyException("Memoria già presente nel database");
		if(!mem.get().getPc().isEmpty())
			throw new AcademyException("Memoria contenuta in un pc; non eliminabile");
		
		//elimino nel database
		memR.delete(mem.get());
	}
	
	////////////////////////////////
	
	@Override
	public List<MemoriaDTO> findAll() throws AcademyException {
		log.debug("findAll memoria");
		List<Memoria> listMemoria = memR.findAll();
		
		return listMemoria.stream()
				.map(mem -> MemoriaDTO.builder()
						.id(mem.getId())
						.descrizione(mem.getDescrizione())
						.spazio(mem.getSpazio())
						.prodotto(buildProdottoDTO(mem.getProdotto()))
						.build()).collect(Collectors.toList());
	}
	
	
	@Override
	public MemoriaDTO getById(Integer id) throws AcademyException {
		log.debug("get Memoria by Id: " + id);
		
		//controllo se il mouse esiste
		Optional<Memoria> m = memR.findById(id);
		if(m.isEmpty())
			throw new AcademyException("Memoria non presente nel database");
		
		Memoria mem = m.get();
		return MemoriaDTO.builder()
				.id(mem.getId())
				.descrizione(mem.getDescrizione())
				.spazio(mem.getSpazio())
				.prodotto(buildProdottoDTO(mem.getProdotto()))
				.build();		
	}
	
	@Override
	public List<String> listaDescrizioniMemorie() throws AcademyException {
		log.debug("listaDescrizioniMemorie, no duplicati");
		
		//recupero tutte le alimentazioni
		List<Memoria> memorie = memR.findAll();
		
		//inserisco tutte le alimetazioni in una lista
		List<String> tutteLeMemorie = new ArrayList<String>();
		for (Memoria memoria : memorie) {
			if(!tutteLeMemorie.contains(memoria.getDescrizione())){
				tutteLeMemorie.add(memoria.getDescrizione());
			}
		}
		
		//mando in output
		return tutteLeMemorie;	
	}
	
	
}
