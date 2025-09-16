package com.betacom.bb.controller;

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
import com.betacom.bb.services.interfaces.IProdottoServices;

@RestController
@RequestMapping("/rest/prodotto")
public class ProdottoController {

	private IProdottoServices prodS;

	public ProdottoController(IProdottoServices prodS) {
		this.prodS = prodS;
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
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true) ProdottoReq req) {

		ResponseBase r = new ResponseBase();
		try {
			prodS.delete(req);
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
}
