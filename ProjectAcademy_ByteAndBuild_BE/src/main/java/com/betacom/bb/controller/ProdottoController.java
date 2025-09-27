package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.requests.ProdottoReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.implementations.ProdottoImpl;
import com.betacom.bb.services.interfaces.IProdottoServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/prodotto")
public class ProdottoController {

    private final ProdottoImpl prodottoImpl;

	private IProdottoServices prodS;

	public ProdottoController(IProdottoServices prodS, ProdottoImpl prodottoImpl) {
		this.prodS = prodS;
		this.prodottoImpl = prodottoImpl;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) ProdottoReq req) {

		ResponseBase r = new ResponseBase();
		try {
			prodS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody (required = true) ProdottoReq req) {

		ResponseBase r = new ResponseBase();
		try {
			prodS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/delete")
	public ResponseBase delete(@RequestParam (required = true) Integer idProd ) {

		ResponseBase r = new ResponseBase();
		try {
			prodS.delete(idProd);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getProdotto")
	public ResponseObject<ProdottoDTO> getProdotto(@RequestParam (required = true) Integer id ){
		ResponseObject<ProdottoDTO> r = new ResponseObject<ProdottoDTO>();
		try {
			r.setDati(prodS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listByFilter")
	public ResponseList<ProdottoDTO> listByFilter(@RequestParam(name="descrizione", required = false)String descrizione){
		ResponseList<ProdottoDTO> p = new ResponseList<ProdottoDTO>();
		if(descrizione == null || descrizione.isBlank())
			descrizione = null;
		
		try {
			p.setDati(prodS.list(descrizione));
		} catch(Exception e) {
			p.setRc(false);
			p.setMsg(e.getMessage());
		}
		return p;
	}
	
	@GetMapping("/listAllProdotto")
	public ResponseList<ProdottoDTO> listAllProdotto(){
		ResponseList<ProdottoDTO> r = new ResponseList<ProdottoDTO>();
		try {
			r.setDati(prodS.listAll());
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllByIdCategoria")
	public ResponseList<ProdottoDTO> listAllByIdCategoria(@RequestParam (required = true) Integer id ){
		ResponseList<ProdottoDTO> r = new ResponseList<ProdottoDTO>();
		try {
			r.setDati(prodS.listAllByIdCategoria(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
