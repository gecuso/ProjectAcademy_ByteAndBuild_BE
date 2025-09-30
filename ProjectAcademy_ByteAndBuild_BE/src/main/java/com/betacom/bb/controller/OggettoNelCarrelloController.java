package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.MouseDTO;
import com.betacom.bb.dto.OggettoNelCarrelloDTO;
import com.betacom.bb.requests.MouseReq;
import com.betacom.bb.requests.OggettoNelCarrelloReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.IMouseService;
import com.betacom.bb.services.interfaces.IOggettoNelCarrelloService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/rest/onc")
@CrossOrigin("*")
@Log4j2
public class OggettoNelCarrelloController {

	private IOggettoNelCarrelloService oncS;

	public OggettoNelCarrelloController(IOggettoNelCarrelloService oncS) {
		this.oncS = oncS;
	}
	
	////////////////////////////////

	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) OggettoNelCarrelloReq req) {		
		ResponseBase r = new ResponseBase();
		log.debug(req);
		try {
			oncS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("/update")
	public ResponseBase update(@RequestBody (required = true) OggettoNelCarrelloReq req) {
		ResponseBase r = new ResponseBase();
		try {
			oncS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true) OggettoNelCarrelloReq req) {
		ResponseBase r = new ResponseBase();
		try {
			oncS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	////////////////////////////////

	@GetMapping("/findAll")
	public ResponseList<OggettoNelCarrelloDTO> findAll() {
		ResponseList<OggettoNelCarrelloDTO> r = new ResponseList<OggettoNelCarrelloDTO>();
		try {
			r.setDati(oncS.findAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getById")
	public ResponseObject<OggettoNelCarrelloDTO> getById(@RequestParam (required = true)  Integer id) {
		ResponseObject<OggettoNelCarrelloDTO> r = new ResponseObject<OggettoNelCarrelloDTO>();
		try {
			r.setDati(oncS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}	
	
	@GetMapping("/getByIdCarrello")
	public ResponseList<OggettoNelCarrelloDTO> getByIdCarrello(@RequestParam (required = true)  Integer id) {
		ResponseList<OggettoNelCarrelloDTO> r = new ResponseList<OggettoNelCarrelloDTO>();
		try {
			r.setDati(oncS.getByIdCarrello(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}	
	
}
