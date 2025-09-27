package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.TastieraDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.TastieraReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ITastieraService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/rest/tastiera")
@CrossOrigin(origins = "*")
@Log4j2
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
	
	@PostMapping("/createTastProd")
	public ResponseBase createTastProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("createTastProd : "+req);
		try {
			tS.createTastProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	@PostMapping("/updateTastProd")
	public ResponseBase updateTastProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("updateTastProd : "+req);
		try {
			tS.updateTastProd(req);
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
	@GetMapping("/findByIdProd")
	public ResponseObject<TastieraDTO> findByIdProd(@RequestParam (required = true) Integer idProd ){
		ResponseObject<TastieraDTO> r = new ResponseObject<TastieraDTO>();
		try {
			r.setDati(tS.findByIdProd(idProd));
		}catch (Exception e) {
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
}
