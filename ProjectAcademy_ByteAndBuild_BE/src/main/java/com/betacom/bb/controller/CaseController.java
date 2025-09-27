package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.CaseDTO;
import com.betacom.bb.requests.CaseReq;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ICaseServices;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/rest/case")
@CrossOrigin(origins = "*")
@Log4j2
public class CaseController {

	private ICaseServices csS;

	public CaseController(ICaseServices csS) {
		this.csS = csS;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) CaseReq req) {
		ResponseBase r = new ResponseBase();
		try {
			csS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
	@PostMapping("/createCaseProd")
	public ResponseBase createCaseProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("createCaseProd : "+req);
		try {
			csS.createCaseProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	@PostMapping("/updateCaseProd")
	public ResponseBase updateCaseProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("updateCaseProd : "+req);
		try {
			csS.updateCaseProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  CaseReq req) {
		ResponseBase r = new ResponseBase();
		try {
			csS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getCase")
	public ResponseObject<CaseDTO> getCase(@RequestParam (required = true) Integer id ){
		ResponseObject<CaseDTO> r = new ResponseObject<CaseDTO>();
		try {
			r.setDati(csS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllCase")
	public ResponseList<CaseDTO> listAllCase(){
		ResponseList<CaseDTO> r = new ResponseList<CaseDTO>();
		try {
			r.setDati(csS.listAll());
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	@GetMapping("/findByIdProd")
	public ResponseObject<CaseDTO> findByIdProd(@RequestParam (required = true) Integer idProd ){
		ResponseObject<CaseDTO> r = new ResponseObject<CaseDTO>();
		try {
			r.setDati(csS.findByIdProd(idProd));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}