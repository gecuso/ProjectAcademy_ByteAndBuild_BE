package com.betacom.bb.utilis;

import java.util.List;
import java.util.stream.Collectors;

import com.betacom.bb.dto.AlimentazioneDTO;
import com.betacom.bb.dto.CaseDTO;
import com.betacom.bb.dto.CategoriaDTO;
import com.betacom.bb.dto.CpuDTO;
import com.betacom.bb.dto.FormatoDTO;
import com.betacom.bb.dto.MarcaDTO;
import com.betacom.bb.dto.MemoriaDTO;
import com.betacom.bb.dto.PcDTO;
import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.dto.RamDTO;
import com.betacom.bb.dto.SchedaGraficaDTO;
import com.betacom.bb.dto.SchedaMadreDTO;
import com.betacom.bb.dto.SistemaRaffreddamentoDTO;
import com.betacom.bb.dto.TastieraDTO;
import com.betacom.bb.models.Alimentazione;
import com.betacom.bb.models.Case;
import com.betacom.bb.models.Categoria;
import com.betacom.bb.models.Cpu;
import com.betacom.bb.models.Formato;
import com.betacom.bb.models.Marca;
import com.betacom.bb.models.Memoria;
import com.betacom.bb.models.Pc;
import com.betacom.bb.models.Prodotto;
import com.betacom.bb.models.Ram;
import com.betacom.bb.models.SchedaGrafica;
import com.betacom.bb.models.SchedaMadre;
import com.betacom.bb.models.SistemaRaffreddamento;
import com.betacom.bb.models.Tastiera;

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
	
	public SchedaMadreDTO buildSchedaMadreDTO(SchedaMadre sm) {
		return SchedaMadreDTO.builder()
				.id(sm.getId())
//				.descrizione(sm.getDescrizione())
				.compatibilita(sm.getCompatibilita())
				.consumo(sm.getConsumo())
				.prodotto(buildProdottoDTO(sm.getProdotto()))
				.formato(buildFormatoDTO(sm.getFormato()))
				.build();
	}
	public SchedaGraficaDTO buildSchedaGraficaDTO(SchedaGrafica sm) {
		return SchedaGraficaDTO.builder()
				.id(sm.getId())
//				.descrizione(sm.getDescrizione())
				.consumo(sm.getConsumo())
				.prodotto(buildProdottoDTO(sm.getProdotto()))
				.build();
	}
	public CpuDTO buildCpuDTO(Cpu c) {
		return CpuDTO.builder()
				.id(c.getId())
//				.descrizione(c.getDescrizione())
				.consumo(c.getConsumo())
				.compatibilita(c.getCompatibilita())
				.prodotto(buildProdottoDTO(c.getProdotto()))
				.build();
	}
	
	public RamDTO buildRamDTO(Ram r) {
		return RamDTO.builder()
				.id(r.getId())
//				.descrizione(r.getDescrizione())
				.consumo(r.getConsumo())
				.prodotto(buildProdottoDTO(r.getProdotto()))
				.build();
		
	}
	public MemoriaDTO buildMemoriaDTO(Memoria m) {
		return MemoriaDTO.builder()
				.id(m.getId())
//				.descrizione(m.getDescrizione())
				.spazio(m.getSpazio())
				.prodotto(buildProdottoDTO(m.getProdotto()))
				.build();
	}
	public CaseDTO buildCaseDTO(Case c) {
		return CaseDTO.builder()
				.id(c.getId())
//				.descrizione(c.getDescrizione())
				.dimensioni(c.getDimensioni())
				.formato(buildFormatoDTO(c.getFormato()))
				.prodotto(buildProdottoDTO(c.getProdotto()))
				.build();		
	}
	public SistemaRaffreddamentoDTO buildSistemaRaffreddamentoDTO(SistemaRaffreddamento s)
	{
		return SistemaRaffreddamentoDTO.builder()
				.id(s.getId())
//				.descrizione(s.getDescrizione())
				.consumo(s.getConsumo())
				.prodotto(buildProdottoDTO(s.getProdotto()))
				.build();		
	}
	public AlimentazioneDTO buildAlimentazioneDTO(Alimentazione a) {
		return AlimentazioneDTO.builder()
				.id(a.getId())
//				.descrizione(a.getDescrizione())
				.potenza(a.getPotenza())
				.prodotto(buildProdottoDTO(a.getProdotto()))
				.build();	
	}
	public List<PcDTO> buildListPcDTO(List<Pc> lp)
	{
		return lp.stream()
				.map((p -> PcDTO.builder()
						.id(p.getId())
//						.descrizione(p.getDescrizione())
						.totConsumo(p.getTotConsumo())
						.prodotto(buildProdottoDTO(p.getProdotto()))
						.schedaMadre(buildSchedaMadreDTO(p.getSchedaMadre()))
						.schedaGrafica(buildSchedaGraficaDTO(p.getSchedaGrafica()))
						.cpu(buildCpuDTO(p.getCpu()))
						.ram(buildRamDTO(p.getRam()))
						.memoria(buildMemoriaDTO(p.getMemoria()))
						.casee(buildCaseDTO(p.getCasee()))
						.sistemaRaffreddamento(buildSistemaRaffreddamentoDTO(p.getSistemaRaffreddamento()))
						.alimentazione(buildAlimentazioneDTO(p.getAlimentazione()))
						.build())
						)
				.collect(Collectors.toList());
				
	}
	public TastieraDTO buildTastieraDTO(Tastiera a) {
		return TastieraDTO.builder()
				.id(a.getId())
//				.descrizione(a.getDescrizione())
				.tipologia(a.getTipologia())
				.collegamento(a.getCollegamento())
				.prodotto(buildProdottoDTO(a.getProdotto()))
				.build();	
	}
	public List<TastieraDTO> buildListTastieraDTO(List<Tastiera> lp)
	{
		return lp.stream()
				.map((a -> TastieraDTO.builder()
						.id(a.getId())
//						.descrizione(a.getDescrizione())
						.tipologia(a.getTipologia())
						.collegamento(a.getCollegamento())
						.prodotto(buildProdottoDTO(a.getProdotto()))
						.build()))
				.collect(Collectors.toList());
				
	}
}