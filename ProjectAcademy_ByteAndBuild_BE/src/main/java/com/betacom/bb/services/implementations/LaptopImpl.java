package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.LaptopDTO;
import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Laptop;
import com.betacom.bb.repositories.ILaptopRepository;
import com.betacom.bb.requests.LaptopReq;
import com.betacom.bb.services.interfaces.ILaptopService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class LaptopImpl implements ILaptopService{
	
	private ILaptopRepository lapR;
	
	public LaptopImpl(ILaptopRepository lapR) {
		this.lapR = lapR;
	}
	
	////////////////////////////////
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(LaptopReq req) throws AcademyException {	
		log.debug("create: " + req);
//		Optional<Laptop> lap = lapR.findByDescrizione(req.getDescrizione());
//		if(lap.isPresent())
//			throw new AcademyException("Laptop già presente nel database");
		
		//controllo dei dati
		Laptop laptop = new Laptop();
//		if(req.getDescrizione() == null)
//			throw new AcademyException("Descrizione non presente, riprovare");
//		laptop.setDescrizione(req.getDescrizione());
		if(req.getCaratteristiche() == null)
			throw new AcademyException("Caratteristiche non presenti, riprovare");
		laptop.setCaratteristiche(req.getCaratteristiche());
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente o non valido, riprovare");
		laptop.setConsumo(req.getConsumo());
		
		if(req.getProdotto().getId() == null)
			throw new AcademyException("Id del prodotto non inserito, riprovare");
		laptop.setProdotto(req.getProdotto());
		
		//salvo nel database
		lapR.save(laptop);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(LaptopReq req) throws AcademyException {
		log.debug("update: " + req);
		
		Optional<Laptop> lap = lapR.findById(req.getId());
		if(lap.isEmpty())
			throw new AcademyException("Laptop non presente nel database");
		
		//controllo dei dati
		Laptop laptop = new Laptop();
		laptop.setId(lap.get().getId());
		//descrizione non può cambiare
//		laptop.setDescrizione(lap.get().getDescrizione());
		if(req.getCaratteristiche() == null)
			throw new AcademyException("Caratteristiche non presenti, riprovare");
		laptop.setCaratteristiche(req.getCaratteristiche());
		if(req.getConsumo() == null || req.getConsumo()<=0)
			throw new AcademyException("Consumo non presente o non valido, riprovare");
		laptop.setConsumo(req.getConsumo());
		//id prodotto non deve cambiare
		laptop.setProdotto(lap.get().getProdotto());
		
		//update nel database
		lapR.save(laptop);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(LaptopReq req) throws AcademyException {
		log.debug("delete: " + req);
		Optional<Laptop> lap = lapR.findById(req.getId());
		if(lap.isEmpty())
			throw new AcademyException("Laptop non presente nel database");
		
		//elimino nel database
		lapR.delete(lap.get());
	}
	
	////////////////////////////////

	@Override
	public List<LaptopDTO> findAll() throws AcademyException {
		log.debug("findAll laptop");
		List<Laptop> listLaptop = lapR.findAll();
		
		return listLaptop.stream()
				.map(lap -> LaptopDTO.builder()
						.id(lap.getId())
//						.descrizione(lap.getDescrizione())
						.caratteristiche(lap.getCaratteristiche())
						.consumo(lap.getConsumo())
						.prodotto(ProdottoDTO.builder()
								.id(lap.getProdotto().getId())
								.build())
						.build()).collect(Collectors.toList());
				
	}

	@Override
	public LaptopDTO getById(Integer id) throws AcademyException {
		log.debug("get Laptop by Id: " + id);
		
		//controllo se il laptop esiste
		Optional<Laptop> l = lapR.findById(id);
		if(l.isEmpty())
			throw new AcademyException("Laptop non presente nel database");
		
		Laptop lap = l.get();
		return LaptopDTO.builder()
				.id(lap.getId())
//				.descrizione(lap.getDescrizione())
				.caratteristiche(lap.getCaratteristiche())
				.consumo(lap.getConsumo())
				.prodotto(ProdottoDTO.builder()
						.id(lap.getProdotto().getId())
						.build())
				.build();
	}
	
	
	
	
	
	
}
