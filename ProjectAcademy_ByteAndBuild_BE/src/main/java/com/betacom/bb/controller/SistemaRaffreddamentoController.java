package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.SistemaRaffreddamentoDTO;
import com.betacom.bb.requests.SistemaRaffreddamentoReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ISistemaRaffreddamentoServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rest/sistemaRaffreddamento")
public class SistemaRaffreddamentoController {

	private ISistemaRaffreddamentoServices srS;

	public SistemaRaffreddamentoController(ISistemaRaffreddamentoServices srS) {
		this.srS = srS;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) SistemaRaffreddamentoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			srS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  SistemaRaffreddamentoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			srS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  SistemaRaffreddamentoReq req) {
		ResponseBase r = new ResponseBase();
		try {
			srS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getSistemaRaffreddamento")
	public ResponseObject<SistemaRaffreddamentoDTO> getSistemaRaffreddamento(@RequestBody (required = true) Integer id ){
		ResponseObject<SistemaRaffreddamentoDTO> r = new ResponseObject<SistemaRaffreddamentoDTO>();
		try {
			r.setDati(srS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllSistemaRaffreddamento")
	public ResponseList<SistemaRaffreddamentoDTO> listAllSistemaRaffreddamento(){
		ResponseList<SistemaRaffreddamentoDTO> r = new ResponseList<SistemaRaffreddamentoDTO>();
		try {
			r.setDati(srS.listAll());
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
