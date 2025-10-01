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

import com.betacom.bb.dto.CarrelloDTO;
import com.betacom.bb.requests.CarrelloReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ICarrelloService;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/carrello")
public class CarrelloController {

	public ICarrelloService carrS;
	
	public CarrelloController(ICarrelloService carrS) {
		this.carrS = carrS;
	}
	
	////////////////////////////////	
	
	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true) CarrelloReq req) {		
		ResponseBase r = new ResponseBase();
		try {
			carrS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true) CarrelloReq req) {
		ResponseBase r = new ResponseBase();
		try {
			carrS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) CarrelloReq req) {
		ResponseBase r = new ResponseBase();
		try {
			carrS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	////////////////////////////////	
	
	@GetMapping("/findAll")
	public ResponseList<CarrelloDTO> findAll() {
		ResponseList<CarrelloDTO> r = new ResponseList<CarrelloDTO>();
		try {
			r.setDati(carrS.findAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getById")
	public ResponseObject<CarrelloDTO> getById(@RequestParam (required = true)  Integer id) {
		ResponseObject<CarrelloDTO> r = new ResponseObject<CarrelloDTO>();
		try {
			r.setDati(carrS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}	
	
	@GetMapping("/getByIdUtente")
	public ResponseObject<CarrelloDTO> getByIdUtente(@RequestParam (required = true)  Integer id) {
		ResponseObject<CarrelloDTO> r = new ResponseObject<CarrelloDTO>();
		try {
			r.setDati(carrS.getByIdUtente(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}
	
	@DeleteMapping("/svuotaCarrello")
	public ResponseBase svuotaCarrello(@RequestParam (required = true)  Integer id) {
		ResponseBase r = new ResponseBase();
		try {
			carrS.svuotaCarrello(id);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}	
	
}
