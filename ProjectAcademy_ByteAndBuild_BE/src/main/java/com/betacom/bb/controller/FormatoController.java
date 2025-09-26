package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.FormatoDTO;
import com.betacom.bb.requests.FormatoReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.IFormatoServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/formato")
public class FormatoController {

	private IFormatoServices fS;

	public FormatoController(IFormatoServices fS) {
		this.fS = fS;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) FormatoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			fS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  FormatoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			fS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getFormato")
	public ResponseObject<FormatoDTO> getFormato(@RequestBody (required = true) Integer id ){
		ResponseObject<FormatoDTO> r = new ResponseObject<FormatoDTO>();
		try {
			r.setDati(fS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllFormato")
	public ResponseList<FormatoDTO> listAllFormato(){
		ResponseList<FormatoDTO> r = new ResponseList<FormatoDTO>();
		try {
			r.setDati(fS.listAll());
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
