package com.betacom.bb.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.CategoriaDTO;
import com.betacom.bb.dto.MarcaDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Marca;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.ICategoriaRepository;
import com.betacom.bb.repositories.IMarcaRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.CategoriaReq;
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
		if(req.getDescrizione() == null)
			throw new AcademyException("Descrizione non presente, riprovare");
		marca.setDescrizione(req.getDescrizione());
//		
//		//il controllo su prodotto non è necessario nel create
//		Optional<Categoria> categoria = cateR.findById(req.getIdCategoria());
//		if(categoria.isEmpty())
//			throw new AcademyException("Categoria non presente o non accettabile, riprovare");
//		
//		//roba brutta ma dovrebbe funzionare
////		List<Categoria> categorie = new ArrayList<Categoria>();
////		categorie.add(req.getIdCategoria());
//		
		List<Categoria> c = new ArrayList<Categoria>();
		marca.setCategoria(c);
		//salvo nel database
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
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void insertCategoriaIntoMarca(MarcaReq m, List<CategoriaReq> lC) throws AcademyException{
		log.debug("insertCategoriaIntoMarca: ");
		
		Optional<Marca> mO = marcaR.findById(m.getId());
		if(mO.isEmpty())
			throw new AcademyException("Marca non presente nel database");
		
		List<Categoria> cl = new ArrayList<Categoria>();
		
		for (CategoriaReq cr : lC) {
			
			Optional<Categoria> cO = cateR.findById(cr.getId());
			if(!cO.isEmpty())
				cl.add(cO.get());
			
		}
		mO.get().setCategoria(cl);
		
		marcaR.save(mO.get());
		
	}
	
	////////////////////////////////
	
	@Override
	public List<MarcaDTO> findAll() throws AcademyException {
		log.debug("findAll marca");
		List<Marca> listaMarca = marcaR.findAll();
		
		return listaMarca.stream()
				.map(mar -> MarcaDTO.builder()
						.id(mar.getId())
						.descrizione(mar.getDescrizione())
						.categoria(mar.getCategoria().stream()
									.map(cat -> CategoriaDTO.builder()
											.id(cat.getId())
											.descrizione(cat.getDescrizione())
											.build()).collect(Collectors.toList()))
						.build()).collect(Collectors.toList());
	}

	@Override
	public MarcaDTO getById(Integer id) throws AcademyException {
		log.debug("get Marca by Id: " + id);
		
		//controllo se il mouse esiste
		Optional<Marca> m = marcaR.findById(id);
		if(m.isEmpty())
			throw new AcademyException("Marca non presente nel database");
		
		Marca mar = m.get();
		return MarcaDTO.builder()
				.id(mar.getId())
				.descrizione(mar.getDescrizione())
				.categoria(mar.getCategoria().stream()
							.map(cat -> CategoriaDTO.builder()
									.id(cat.getId())
									.descrizione(cat.getDescrizione())
									.build()).collect(Collectors.toList()))
				.build();
	}
	
	@Override
	public List<String> findAllMarche() throws AcademyException {
		log.debug("findAllMarche, no duplicati");
		
		//recupero tutte le marche
		List<Marca> marche = marcaR.findAll();
		
		//inserisco tutte le marche in una lista
		List<String> tutteLeMarche = new ArrayList<String>();
		for (Marca marca : marche) {
			if(!tutteLeMarche.contains(marca.getDescrizione())) {
				tutteLeMarche.add(marca.getDescrizione());
			}
		}
		
		//mando in output
		return tutteLeMarche;
	}
	
}
