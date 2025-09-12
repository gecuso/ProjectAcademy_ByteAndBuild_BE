package com.betacom.bb.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Marca;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.ICategoriaRepository;
import com.betacom.bb.repositories.IMarcaRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.MarcaReq;
import com.betacom.bb.services.interfaces.IMarcaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MarcaImpl implements IMarcaService{

	private IMarcaRepository marcaR;
	private ICategoriaRepository cateR;
	private IProdottoRepository prodR;

	public MarcaImpl(IMarcaRepository marcaR, ICategoriaRepository cateR, IProdottoRepository prodR) {
		this.marcaR = marcaR;
		this.cateR = cateR;
		this.prodR = prodR;
	}
	
	////////////////////////////////
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(MarcaReq req) throws AcademyException {
		log.debug("create: " + req);
		//non controllo che sia presente può essere duplicato
		
		//controllo dei dati
		Marca marca = new Marca();
		if(req.getDescrizione().isEmpty())
			throw new AcademyException("Descrizione non presente, riprovare");
		marca.setDescrizione(req.getDescrizione());
		
		//il controllo su prodotto non è necessario nel create
		Optional<Categoria> categoria = cateR.findById(req.getCategoria().getId());
		if(categoria.isEmpty())
			throw new AcademyException("Categoria non presente o non accettabile, riprovare");
		
		//roba brutta ma dovrebbe funzionare
		List<Categoria> categorie = new ArrayList<Categoria>();
		categorie.add(req.getCategoria());
		marca.setCategoria(categorie);
		
		//salvo nel database
		marcaR.save(marca);
		
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(MarcaReq req) throws AcademyException {
		log.debug("update: " + req);
		Optional<Marca> mar = marcaR.findById(req.getId());
		if(mar.isEmpty())
			throw new AcademyException("Marca non presente nel database");
		
		//controllo dei dati
		Marca marca = new Marca();
		marca.setId(mar.get().getId());
		//descrizione non può cambiare
		marca.setDescrizione(mar.get().getDescrizione());
		//id prodotto non deve cambiare
		marca.setProdotto(mar.get().getProdotto());
		
		//controllo che la categoria che voglio usare esista
		Optional<Categoria> categoria = cateR.findById(req.getCategoria().getId());
		if(categoria.isEmpty())
			throw new AcademyException("Categoria non presente o non accettabile, riprovare");

		//controllo se esiste già la marca uguale
		Optional<List<Marca>> marcheUsate = marcaR.findAllByDescrizione(req.getDescrizione());
		if(!marcheUsate.isEmpty()) {
			//controllo se usate gia quella categoria
			for(int i=0; i<marcheUsate.get().size(); i++) {
				if(marcheUsate.get().get(i).getCategoria().equals(req.getCategoria())) {
					throw new AcademyException("Categoria già usata con questa marca, evita duplicati...");
				}
			}
		}
		
		//CONTROLLA CHE FUNZIONI QUESTA PARTE
		List<Categoria> salvaCategoria = new ArrayList<Categoria>();
		salvaCategoria.add(req.getCategoria());
		marca.setCategoria(salvaCategoria);
		
		//update nel database
		marcaR.save(marca);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(MarcaReq req) throws AcademyException {
		log.debug("delete: " + req);
		Optional<Marca> mar = marcaR.findById(req.getId());
		if(mar.isEmpty())
			throw new AcademyException("Marca non presente nel database");
		
		int count = 0;
		List<Prodotto> prodotti = prodR.findAll();
		for (Prodotto prodotto : prodotti) {
			if(prodotto.getMarca().equals(mar.get().getId())) {
				count = 1;
				break;
			}
		}
		//guardo se è usata
		if(count>0)
			throw new AcademyException("La marca viene usata da alcuni prodotti, non posso eliminarla");
	
		//elimino nel database
		marcaR.delete(mar.get());
		
	}
	
	////////////////////////////////
	
}
