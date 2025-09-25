package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.SchedaMadreDTO;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.SchedaMadreReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ISchedaMadreServices;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/rest/schedaMadre")
@CrossOrigin(origins = "*")
@Log4j2
public class SchedaMadreController {

	private ISchedaMadreServices smS;

	public SchedaMadreController(ISchedaMadreServices smS) {
		this.smS = smS;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) SchedaMadreReq req) {
		ResponseBase r = new ResponseBase();
		try {
			smS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
	
	@PostMapping("/createSchMdrProd")
	public ResponseBase createSchMdrProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("controller alim : "+req);
		try {
			smS.createSchMdrProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  SchedaMadreReq req) {
		ResponseBase r = new ResponseBase();
		try {
			smS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  SchedaMadreReq req) {
		ResponseBase r = new ResponseBase();
		try {
			smS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getSchedaMadre")
	public ResponseObject<SchedaMadreDTO> getSchedaMadre(@RequestParam (required = true) Integer id ){
		ResponseObject<SchedaMadreDTO> r = new ResponseObject<SchedaMadreDTO>();
		try {
			r.setDati(smS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllSchedaMadre")
	public ResponseList<SchedaMadreDTO> listAllSchedaMadre(){
		ResponseList<SchedaMadreDTO> r = new ResponseList<SchedaMadreDTO>();
		try {
			r.setDati(smS.listAll());
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
