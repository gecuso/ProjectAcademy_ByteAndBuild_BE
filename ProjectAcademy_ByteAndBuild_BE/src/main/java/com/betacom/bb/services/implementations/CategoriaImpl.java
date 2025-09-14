package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.bb.dto.CategoriaDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Marca;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.repositories.ICategoriaRepository;
import com.betacom.bb.repositories.IMarcaRepository;
import com.betacom.bb.repositories.IProdottoRepository;
import com.betacom.bb.requests.CategoriaReq;
import com.betacom.bb.services.interfaces.ICategoriaServices;
import com.betacom.bb.utilis.Utilities;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CategoriaImpl extends Utilities implements ICategoriaServices{

	private ICategoriaRepository catR;
	private IProdottoRepository prodR;
	private IMarcaRepository marR;
	
	public CategoriaImpl(ICategoriaRepository catR) {
		super();
		this.catR = catR;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(CategoriaReq req) throws AcademyException {
		log.debug("create: " + req);
		
		if(req.getDescrizione() == null)throw new AcademyException("necessaria una descrizione");
		
		Optional<Categoria> cat = catR.findByDescrizione(req.getDescrizione());
		if(!cat.isEmpty())throw new AcademyException("questa categoria esiste gia");
		
		Categoria c = new Categoria();
		c.setDescrizione(req.getDescrizione());
		catR.save(c);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(CategoriaReq catReq) throws AcademyException {
		log.debug("delete: " + catReq);
		Optional<Categoria> cat = catR.findById(catReq.getId());
		if(cat.isEmpty())throw new AcademyException("categoria non esistente");
		
		List<Prodotto> lp = prodR.findAll();
		for(Prodotto p : lp) {
			if(p.getCategoria().getId()==catReq.getId()) {
				throw new AcademyException("non è possibile cancellare la categoria, ci sono dei prodotti di quella categoria");
			}
		}
		
		List<Marca> lm= marR.findAll();
		for(Marca m: lm)
		{
			if(m.getCategoria().contains(cat.get())) {
				m.getCategoria().remove(cat.get());
				marR.save(m);
			}
		}
		
		catR.delete(cat.get());
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(CategoriaReq catReq) throws AcademyException {
		log.debug("update: " + catReq);
		Optional<Categoria> cat = catR.findById(catReq.getId());
		if(cat.isEmpty())throw new AcademyException("categoria: "+catReq.getId()+" non esistente");
		
		List<Categoria> lc = catR.findAll();
		for(Categoria c: lc) {
			if(c.getDescrizione().equals(catReq.getDescrizione()))throw new AcademyException("esiste gia una categoria con lo stesso nome");
		}
		Categoria c = cat.get();
		c.setDescrizione(catReq.getDescrizione());
		catR.save(c);
		
	}

	@Override
	public CategoriaDTO getById(Integer id) throws AcademyException {
		
		Optional<Categoria> catO = catR.findById(id);
		
		if (catO.isEmpty())
			throw new AcademyException("pc non trovato in database :" + id);
		Categoria p = catO.get();
	
		return CategoriaDTO.builder()
				.id(p.getId())
				.descrizione(p.getDescrizione())
				.build();
	}

	@Override
	public List<CategoriaDTO> listAll() {
		List<Categoria> lc = catR.findAll();
		return buildListCategoriaDTO(lc);
	}

}
