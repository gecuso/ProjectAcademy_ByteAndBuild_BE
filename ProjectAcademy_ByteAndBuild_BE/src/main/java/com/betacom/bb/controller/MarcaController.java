package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.MarcaDTO;
import com.betacom.bb.requests.MarcaReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.IMarcaService;



@RestController
@RequestMapping("/rest/marca")
public class MarcaController {

	private IMarcaService marS;

	public MarcaController(IMarcaService marS) {
		this.marS = marS;
	}
	
	////////////////////////////////
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) MarcaReq req) {		
		ResponseBase r = new ResponseBase();
		try {
			marS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/update")
	public ResponseBase update(@RequestBody (required = true) MarcaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			marS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true) MarcaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			marS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	////////////////////////////////
	
	@GetMapping("/findAll")
	public ResponseList<MarcaDTO> findAll() {
		ResponseList<MarcaDTO> r = new ResponseList<MarcaDTO>();
		try {
			r.setDati(marS.findAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getById")
	public ResponseObject<MarcaDTO> getById(@RequestParam (required = true)  Integer id) {
		ResponseObject<MarcaDTO> r = new ResponseObject<MarcaDTO>();
		try {
			r.setDati(marS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}	
	
	@GetMapping("/listMarche")
	public ResponseList<String> findAllMarche() {
		ResponseList<String> r = new ResponseList<String>();
		try {
			r.setDati(marS.findAllMarche());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
}
