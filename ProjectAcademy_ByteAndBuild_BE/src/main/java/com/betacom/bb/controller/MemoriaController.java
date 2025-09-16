package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.MemoriaDTO;
import com.betacom.bb.requests.MemoriaReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.IMemoriaService;

@RestController
@RequestMapping("/rest/memoria")
public class MemoriaController {

	private IMemoriaService memS;

	public MemoriaController(IMemoriaService memS) {
		this.memS = memS;
	}
	
	////////////////////////////////
	
	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true) MemoriaReq req) {		
		ResponseBase r = new ResponseBase();
		try {
			memS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true) MemoriaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			memS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) MemoriaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			memS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	////////////////////////////////
	
	@GetMapping("/findAll")
	public ResponseList<MemoriaDTO> findAll() {
		ResponseList<MemoriaDTO> r = new ResponseList<MemoriaDTO>();
		try {
			r.setDati(memS.findAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getById")
	public ResponseObject<MemoriaDTO> getById(@RequestParam (required = true)  Integer id) {
		ResponseObject<MemoriaDTO> r = new ResponseObject<MemoriaDTO>();
		try {
			r.setDati(memS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}
		
}
