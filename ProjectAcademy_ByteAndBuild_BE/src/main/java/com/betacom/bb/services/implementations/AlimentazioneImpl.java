package com.betacom.bb.services.implementations;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.AlimentazioneDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Alimentazione;
import com.betacom.bb.repositories.IAlimentazioneRepository;
import com.betacom.bb.requests.AlimentazioneReq;
import com.betacom.bb.services.interfaces.IAlimentazioneServices;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AlimentazioneImpl implements IAlimentazioneServices{

	private IAlimentazioneRepository alimR;


	public AlimentazioneImpl(IAlimentazioneRepository alimR) {
		this.alimR = alimR;
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(AlimentazioneReq req) throws AcademyException {
		log.debug("create: " + req);
		Alimentazione alim = new Alimentazione();
		Optional<Alimentazione> a = alimR.findByDescrizione(req.getDescrizione());
		if(a.isPresent())
			throw new AcademyException("Alimentatore già esistente nel database");
		
		if(req.getDescrizione().isEmpty())
			throw new AcademyException("Descrizione non presente, riprova");
		alim.setDescrizione(req.getDescrizione());
		
		if(req.getPotenza() == null || req.getPotenza()<=0)
			throw new AcademyException("Potenza non presente, riprova");
		alim.setPotenza(req.getPotenza());
		
		if(req.getProdotto().getId() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		alim.setProdotto(req.getProdotto());
		
		alimR.save(alim);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(AlimentazioneReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<Alimentazione> a = alimR.findById(req.getId());
		
		if(a.isEmpty())
			throw new AcademyException("Alimentatore non esistente");
		Alimentazione alim = a.get();
		
		if(req.getPotenza() == null || req.getPotenza()<=0)
			throw new AcademyException("Potenza non presente, riprova");
		alim.setPotenza(req.getPotenza());
		
		alimR.save(alim);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(AlimentazioneReq req) throws AcademyException {
		log.debug("delete :" + req);
		Optional<Alimentazione> a = alimR.findById(req.getId());
		
		if(a.isEmpty())
			throw new AcademyException("Alimentazione non esistente");
	
		if(!a.get().getPc().isEmpty())
			throw new AcademyException("Alimentazione contenutaa in un pc, non eliminabile");
		
		alimR.delete(a.get());
	}
	
	
	@Override
	public AlimentazioneDTO getById(Integer id) throws AcademyException {
		// TODO Auto-generated method stub
		return null;
	}
}
