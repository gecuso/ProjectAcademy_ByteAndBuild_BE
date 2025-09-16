package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.TastieraDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.TastieraReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ITastieraService;

@RestController
@RequestMapping("/rest/tastiera")
public class TastieraController {

	private ITastieraService tS;
	
	
	public TastieraController(ITastieraService tS) {
		this.tS = tS;
	}

	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true)  TastieraReq req) {
		ResponseBase r = new ResponseBase();
		try {
			tS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody (required = true)  TastieraReq req) {
		ResponseBase r = new ResponseBase();
		try {
			tS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true) TastieraReq req) throws AcademyException {
		ResponseBase r = new ResponseBase();
		try {
			tS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/findAll")
	public ResponseList<TastieraDTO> findAll() {
		ResponseList<TastieraDTO> r = new ResponseList<TastieraDTO>();
		try {
			r.setDati(tS.findAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getById")
	public ResponseObject<TastieraDTO> getById(@RequestParam (required = true)  Integer id) {
		ResponseObject<TastieraDTO> r = new ResponseObject<TastieraDTO>();
		try {
			r.setDati(tS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}
	
	@GetMapping("/listTastiere")
	public ResponseList<String> findAllTastiere() {
		ResponseList<String> r = new ResponseList<String>();
		try {
			r.setDati(tS.listaDescrizioniTastiere());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
}
