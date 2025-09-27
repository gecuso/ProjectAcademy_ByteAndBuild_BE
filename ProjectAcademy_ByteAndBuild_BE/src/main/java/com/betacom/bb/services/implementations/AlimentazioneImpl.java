package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.AlimentazioneDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Alimentazione;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.IAlimentazioneRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.AlimentazioneReq;
import com.betacom.bb.services.interfaces.IAlimentazioneServices;
import com.betacom.bb.services.interfaces.IProdottoServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AlimentazioneImpl extends Utilities implements IAlimentazioneServices{

	private IAlimentazioneRepository alimR;
	private IProdottoRepository prodR;
	private IProdottoServices prodS;

	public AlimentazioneImpl(IAlimentazioneRepository alimR, IProdottoRepository prodR, IProdottoServices prodS) {
		super();
		this.alimR = alimR;
		this.prodR = prodR;
		this.prodS = prodS;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(AlimentazioneReq req) throws AcademyException {
		log.debug("create: " + req);
		Alimentazione alim = new Alimentazione();
		Optional<Alimentazione> a = alimR.findByDescrizione(req.getDescrizione());
		if(a.isPresent())
			throw new AcademyException("Alimentatore già esistente nel database");
		if(req.getDescrizione()== null)
			throw new AcademyException("Descrizione non presente, riprova");
		alim.setDescrizione(req.getDescrizione());
		
		if(req.getPotenza() == null || req.getPotenza()<=0)
			throw new AcademyException("Potenza non presente, riprova");
		alim.setPotenza(req.getPotenza());
		
		if(req.getIdProdotto() == null)
			throw new AcademyException("Prodotto non presente, riprova");
		Optional<Prodotto> p = prodR.findById(req.getIdProdotto());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non presente nel database");
		alim.setProdotto(p.get());
		
		alimR.save(alim);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void createAlimProd(GeneralReq req)throws AcademyException{
		log.debug(req);
		Integer idprod = prodS.create(req.getProdReq());
		
		req.getAlimReq().setDescrizione(req.getProdReq().getDescrizione());
		req.getAlimReq().setIdProdotto(idprod);
		
		create(req.getAlimReq());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateAlimProd(GeneralReq req) throws AcademyException {
		log.debug(req);
		prodS.update(req.getProdReq());
		
		req.getAlimReq().setDescrizione(req.getProdReq().getDescrizione());
		update(req.getAlimReq());
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteAlimProd(GeneralReq req) throws AcademyException {
		log.debug(req);
		delete(req.getAlimReq());
		prodS.delete(req.getProdReq().getId());
		throw new AcademyException("fatto");
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
		log.debug("getAlimentazione: " + id);
		Optional<Alimentazione> alim = alimR.findById(id);
		
		if(alim.isEmpty())
			throw new AcademyException("Alimentatore non esistente");
		Alimentazione a = alim.get();

		return AlimentazioneDTO.builder()
				.id(a.getId())
				.descrizione(a.getDescrizione())
				.potenza(a.getPotenza())
				.prodotto(buildProdottoDTO(a.getProdotto()))
				.build();
	}

	@Override
	public List<AlimentazioneDTO> listAll() {
		log.debug("lisAll di Alimentazione: ");
		List<Alimentazione> lA = alimR.findAll();
		
		return lA.stream()
				.map(a -> AlimentazioneDTO.builder()
						.id(a.getId())
						.descrizione(a.getDescrizione())
						.potenza(a.getPotenza())
						.prodotto(buildProdottoDTO(a.getProdotto()))
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public AlimentazioneDTO findByIdProd(Integer idProd) throws AcademyException {
		Alimentazione alim = alimR.findByIdProd(idProd);
		return buildAlimentazioneDTO(alim);
	}
	
//	public AlimentazioneDTO findByIdProd(Integer idP) {
//		List<AlimentazioneDTO> lA = listAll();
//		AlimentazioneDTO alim=lA.stream().filter(a->a.getProdotto().getId()==idP).findFirst().get();
//		return alim;
//		
//	}
}
