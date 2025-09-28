package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Marca;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.ICategoriaRepository;
import com.betacom.bb.repositories.IMarcaRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.ProdottoReq;
import com.betacom.bb.services.interfaces.IProdottoServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProdottoImpl extends Utilities implements IProdottoServices{

	private IProdottoRepository prodR;
	private ICategoriaRepository catR;
	private IMarcaRepository marcaR;

	public ProdottoImpl(IProdottoRepository prodR, ICategoriaRepository catR, IMarcaRepository marcaR) {
		super();
		this.prodR = prodR;
		this.catR = catR;
		this.marcaR = marcaR;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer create(ProdottoReq req) throws AcademyException {
		log.debug("create: " + req);
		Prodotto prod = new Prodotto();
		Optional<Prodotto> p = prodR.findByDescrizione(req.getDescrizione());
		if(p.isPresent())
			throw new AcademyException("Prodotto già esistente nel database");
		
		if(req.getDescrizione() == null)
			throw new AcademyException("Descrizione non presente, riprova");
		prod.setDescrizione(req.getDescrizione());
		
		if(req.getIdCategoria() == null)
			throw new AcademyException("Categoria non presente, riprova");
		Optional<Categoria> c = catR.findById(req.getIdCategoria());
		if(c.isEmpty())
			throw new AcademyException("Categoria non presente, riprova");
		prod.setCategoria(c.get());
		
		if(req.getIdMarca() == null)
			throw new AcademyException("Categoria non presente, riprova");
		Optional<Marca> m = marcaR.findById(req.getIdMarca());
		if(m.isEmpty())
			throw new AcademyException("Marca non presente, riprova");
		prod.setMarca(m.get());
		
		if(req.getImg() == null)
			throw new AcademyException("Immagine non presente, riprova");
		prod.setImg(req.getImg());
		
		if(req.getCosto() == null || req.getCosto()<0)
			throw new AcademyException("Costo non presente, riprova");
		prod.setCosto(req.getCosto());
		
		if(req.getPrezzo() == null || req.getPrezzo()<0)
			throw new AcademyException("Prezzo non presente, riprova");
		prod.setPrezzo(req.getPrezzo());
		
		if(req.getQuantita() == null || req.getQuantita()<0)
			throw new AcademyException("Quantità errata, riprova");
		prod.setQuantita(req.getQuantita());	
		
		return prodR.save(prod).getId();
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(ProdottoReq req) throws AcademyException {
		log.debug("update: " + req);
		Optional<Prodotto> p = prodR.findById(req.getId());
		if(p.isEmpty())
			throw new AcademyException("Prodotto non esistente nel database");
		Prodotto prod = p.get();
		

		if(req.getDescrizione() == null) {
			throw new AcademyException("Descrizione non presente, riprova");
		}
		List<Prodotto> lp = prodR.findAll();
		for (Prodotto pr : lp) {
			if(pr.getDescrizione().equalsIgnoreCase(req.getDescrizione())&&pr.getId()!=req.getId())
				throw new AcademyException("Esiste gia un Prodotto con la stessa descrizione");
			}
		prod.setDescrizione(req.getDescrizione());
		
		if(req.getIdCategoria() == null)
			throw new AcademyException("Categoria non presente, riprova");
		Optional<Categoria> c = catR.findById(req.getIdCategoria());
		if(c.isEmpty())
			throw new AcademyException("Categoria non presente, riprova");
		prod.setCategoria(c.get());
		
		if(req.getIdMarca() == null)
			throw new AcademyException("Categoria non presente, riprova");
		Optional<Marca> m = marcaR.findById(req.getIdMarca());
		if(m.isEmpty())
			throw new AcademyException("Marca non presente, riprova");
		prod.setMarca(m.get());
		
		if(req.getImg() == null)
			throw new AcademyException("Immagine non presente, riprova");
		prod.setImg(req.getImg());
		
		if(req.getCosto() == null || req.getCosto()<0)
			throw new AcademyException("Costo non presente, riprova");
		prod.setCosto(req.getCosto());
		
		if(req.getPrezzo() == null || req.getPrezzo()<0)
			throw new AcademyException("Prezzo non presente, riprova");
		prod.setPrezzo(req.getPrezzo());
		
		if(req.getQuantita() == null || req.getQuantita()<0)
			throw new AcademyException("Quantità errata, riprova");
		prod.setQuantita(req.getQuantita());	
		
		prodR.save(prod);
		
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(Integer idProd) throws AcademyException {
		log.debug("delete :" + idProd);
		Optional<Prodotto> p = prodR.findById(idProd);
		
		if(p.isEmpty())
			throw new AcademyException("Prodotto non esistente");
	
		prodR.delete(p.get());
	}
	
	
	@Override
	public ProdottoDTO getById(Integer id) throws AcademyException {
		log.debug("getProdotto: " + id);
		Optional<Prodotto> prod = prodR.findById(id);
		
		if(prod.isEmpty())
			throw new AcademyException("Prodotto non esistente");
		Prodotto p = prod.get();

		return ProdottoDTO.builder()
				.id(p.getId())
				.descrizione(p.getDescrizione())
				.costo(p.getCosto())
				.prezzo(p.getPrezzo())
				.quantita(p.getQuantita())
				.img(p.getImg())
				.categoria(buildCategoriaDTO(p.getCategoria()))
				.marca(buildMarcaDTO(p.getMarca()))
				.build();
	}

	@Override
	public List<ProdottoDTO> list(String descrizione) {
		log.debug("listByFilter");
		List<Prodotto> lP = prodR.searchByFilter(descrizione);
		return lP.stream()
				.map(p -> ProdottoDTO.builder()
						.id(p.getId())
						.descrizione(p.getDescrizione())
						.costo(p.getCosto())
						.prezzo(p.getPrezzo())
						.quantita(p.getQuantita())
						.img(p.getImg())
						.categoria(buildCategoriaDTO(p.getCategoria()))
						.marca(buildMarcaDTO(p.getMarca()))
						.build())
				.collect(Collectors.toList());
	}
	
	@Override
	public List<ProdottoDTO> listAll() {
		log.debug("lisAll di Alimentazione: ");
		List<Prodotto> lP = prodR.findAll();
		
		return lP.stream()
				.map(p -> ProdottoDTO.builder()
						.id(p.getId())
						.descrizione(p.getDescrizione())
						.costo(p.getCosto())
						.prezzo(p.getPrezzo())
						.quantita(p.getQuantita())
						.img(p.getImg())
						.categoria(buildCategoriaDTO(p.getCategoria()))
						.marca(buildMarcaDTO(p.getMarca()))
						.build())
				.collect(Collectors.toList());
	}
	
	
	@Override
	public List<ProdottoDTO> listAllByIdCategoria(Integer idCategoria) {
		log.debug("list all by id categoria: " + idCategoria);
		List<Prodotto> lP = prodR.findAllByCategoria(idCategoria);
		
		return lP.stream()
				.map(p -> ProdottoDTO.builder()
						.id(p.getId())
						.descrizione(p.getDescrizione())
						.costo(p.getCosto())
						.prezzo(p.getPrezzo())
						.quantita(p.getQuantita())
						.img(p.getImg())
						.categoria(buildCategoriaDTO(p.getCategoria()))
						.marca(buildMarcaDTO(p.getMarca()))
						.build())
				.collect(Collectors.toList());
	}
}
	
