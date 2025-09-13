package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.CategoriaDTO;
import com.betacom.bb.requests.CategoriaReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ICategoriaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rest/categoria")
public class CategoriaController {

	private ICategoriaService catS;

	public CategoriaController(ICategoriaService catS) {
		this.catS = catS;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) CategoriaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			catS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  CategoriaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			catS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  CategoriaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			catS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getCategoria")
	public ResponseObject<CategoriaDTO> getCategoria(@RequestBody (required = true) Integer id ){
		ResponseObject<CategoriaDTO> r = new ResponseObject<CategoriaDTO>();
		try {
			r.setDati(catS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllCategoria")
	public ResponseList<CategoriaDTO> listAllCategoria(){
		ResponseList<CategoriaDTO> r = new ResponseList<CategoriaDTO>();
		try {
			r.setDati(catS.listAll());
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
