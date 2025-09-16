package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.CaseDTO;
import com.betacom.bb.requests.CaseReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ICaseServices;

@RestController
@RequestMapping("/rest/case")
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
	

	@GetMapping("/listCases")
	public ResponseList<String> findAllCases() {
		ResponseList<String> r = new ResponseList<String>();
		try {
			r.setDati(csS.listaDescrizioniCases());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
}