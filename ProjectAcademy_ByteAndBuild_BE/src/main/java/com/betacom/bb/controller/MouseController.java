package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.MouseDTO;
import com.betacom.bb.requests.MouseReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.IMouseService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rest/mouse")
public class MouseController {
	
	private IMouseService mouS;

	public MouseController(IMouseService mouS) {
		this.mouS = mouS;
	}
	
	////////////////////////////////

	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true) MouseReq req) {		
		ResponseBase r = new ResponseBase();
		try {
			mouS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true) MouseReq req) {
		ResponseBase r = new ResponseBase();
		try {
			mouS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) MouseReq req) {
		ResponseBase r = new ResponseBase();
		try {
			mouS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	////////////////////////////////
	
	@GetMapping("/findAll")
	public ResponseList<MouseDTO> findAll() {
		ResponseList<MouseDTO> r = new ResponseList<MouseDTO>();
		try {
			r.setDati(mouS.findAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getById")
	public ResponseObject<MouseDTO> getById(@RequestParam (required = true)  Integer id) {
		ResponseObject<MouseDTO> r = new ResponseObject<MouseDTO>();
		try {
			r.setDati(mouS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}
	
}
