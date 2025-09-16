package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.LaptopDTO;
import com.betacom.bb.requests.LaptopReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ILaptopService;


@RestController
@RequestMapping("/rest/laptop")
public class LaptopController {

	private ILaptopService laptS;

	public LaptopController(ILaptopService laptS) {
		this.laptS = laptS;
	}
	
	////////////////////////////////
	
	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true) LaptopReq req) {		
		ResponseBase r = new ResponseBase();
		try {
			laptS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true) LaptopReq req) {
		ResponseBase r = new ResponseBase();
		try {
			laptS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) LaptopReq req) {
		ResponseBase r = new ResponseBase();
		try {
			laptS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	////////////////////////////////
	
	@GetMapping("/findAll")
	public ResponseList<LaptopDTO> findAll() {
		ResponseList<LaptopDTO> r = new ResponseList<LaptopDTO>();
		try {
			r.setDati(laptS.findAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getById")
	public ResponseObject<LaptopDTO> getById(@RequestParam (required = true)  Integer id) {
		ResponseObject<LaptopDTO> r = new ResponseObject<LaptopDTO>();
		try {
			r.setDati(laptS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}	

}
