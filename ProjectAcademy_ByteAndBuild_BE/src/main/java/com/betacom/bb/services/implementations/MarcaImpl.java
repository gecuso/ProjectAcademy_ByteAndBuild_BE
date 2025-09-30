package com.betacom.bb.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.MarcaDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Marca;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.ICategoriaRepository;
import com.betacom.bb.repositories.IMarcaRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.MarcaReq;
import com.betacom.bb.services.interfaces.IMarcaService;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MarcaImpl extends Utilities implements IMarcaService{

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
		Optional<Categoria> categoria = cateR.findById(req.getIdCategoria());
		if(categoria.isEmpty())
			throw new AcademyException("Categoria non presente o non accettabile, riprovare");
		
		//controllo se esiste già la marca uguale
		Optional<List<Marca>> marcheUsate = marcaR.findAllByDescrizione(req.getDescrizione());
			if(!marcheUsate.isEmpty()) {
				//controllo se usate gia quella categoria
				for(int i=0; i<marcheUsate.get().size(); i++) {
					//controllo se la marca(i) ha categoria già usata
					if(marcheUsate.get().get(i).getCategoria().get(0).getId().equals(req.getIdCategoria())) {
						throw new AcademyException("Categoria già usata con questa marca, evita duplicati...");
				}
			}
		}

		//CONTROLLA CHE FUNZIONI QUESTA PARTE
		List<Categoria> salvaCategoria = new ArrayList<Categoria>();
		salvaCategoria.add(cateR.findById(req.getIdCategoria()).get()); //già controllato sopra
		marca.setCategoria(salvaCategoria);
		
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
		Optional<Categoria> categoria = cateR.findById(req.getIdCategoria());
		if(categoria.isEmpty())
			throw new AcademyException("Categoria non presente o non accettabile, riprovare");

		//CONTROLLA CHE FUNZIONI QUESTA PARTE
		List<Categoria> salvaCategoria = mar.get().getCategoria();
		
		if(salvaCategoria.contains(categoria.get()))
			throw new AcademyException("Categoria già usata con questa marca, evita duplicati...");
		salvaCategoria.add(categoria.get()); //già controllato sopra
		
		
//		//controllo se esiste già la marca uguale
//		Optional<List<Marca>> marcheUsate = marcaR.findAllByDescrizione(req.getDescrizione());
//		if(!marcheUsate.isEmpty()) {
//			//controllo se usate gia quella categoria
//			for(int i=0; i<marcheUsate.get().size(); i++) {
//				//controllo se la marca(i) ha categoria già usata
//				if(marcheUsate.get().get(i).getCategoria().get(0).getId().equals(req.getIdCategoria())) {
//					throw new AcademyException("Categoria già usata con questa marca, evita duplicati...");
//				}
//				salvaCategoria.add(marcheUsate.get().get(i).getCategoria().get(0));
//			}
//		}
		
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
	
	@Override
	public List<MarcaDTO> findAll() throws AcademyException {
		log.debug("findAll marca");
		List<Marca> listaMarca = marcaR.findAll();
		
		return listaMarca.stream()
				.map(mar -> MarcaDTO.builder()
						.id(mar.getId())
						.descrizione(mar.getDescrizione())
						.categoria(buildListCategoriaDTO(mar.getCategoria()))
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
		return buildMarcaDTO(mar);
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
	
	
	@Override
	public List<MarcaDTO> findByCategoria(Integer idCategoria) throws AcademyException {
	    List<Marca> marche = marcaR.findByIdCategoria(idCategoria);
	    return marche.stream()
	            .map(this::buildMarcaDTO)
	            .collect(Collectors.toList());
	}
}