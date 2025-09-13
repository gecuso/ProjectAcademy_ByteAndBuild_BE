package com.betacom.bb.utilis;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.bb.dto.CategoriaDTO;
import com.betacom.bb.dto.FormatoDTO;
import com.betacom.bb.dto.MarcaDTO;
import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Formato;
import com.betacom.bb.models.Marca;
import com.betacom.bb.models.Prodotto;

public class Utilities {

	public ProdottoDTO buildProdottoDTO(Prodotto p) {
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
	
	public FormatoDTO buildFormatoDTO(Formato f) {
		return FormatoDTO.builder()
				.id(f.getId())
				.descrizione(f.getDescrizione())
				.build();
	}
	
	public CategoriaDTO buildCategoriaDTO(Categoria c) {
		return CategoriaDTO.builder()
				.id(c.getId())
				.descrizione(c.getDescrizione())
				.build();
	}
	
	public List<CategoriaDTO> buildListCategoriaDTO(List<Categoria> cat) {
		return cat.stream()
				.map(c -> CategoriaDTO.builder()
						.id(c.getId())
						.descrizione(c.getDescrizione())
						.build())
				.collect(Collectors.toList());
	}
	
	public MarcaDTO buildMarcaDTO(Marca m) {
		return MarcaDTO.builder()
				.id(m.getId())
				.descrizione(m.getDescrizione())
				.categoria(buildListCategoriaDTO(m.getCategoria()))
				.build();
	}
	
}
