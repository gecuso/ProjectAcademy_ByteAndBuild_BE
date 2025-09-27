package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.RamDTO;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.RamReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.IRamServices;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/rest/ram")
@CrossOrigin(origins = "*")
@Log4j2
public class RamController {

	private IRamServices ramS;

	public RamController(IRamServices ramS) {
		this.ramS = ramS;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) RamReq req) {
		ResponseBase r = new ResponseBase();
		try {
			ramS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
	
	@PostMapping("/createRamProd")
	public ResponseBase createRamProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("controller alim : "+req);
		try {
			ramS.createRamProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  RamReq req) {
		ResponseBase r = new ResponseBase();
		try {
			ramS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  RamReq req) {
		ResponseBase r = new ResponseBase();
		try {
			ramS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getRam")
	public ResponseObject<RamDTO> getRam(@RequestParam (required = true) Integer id ){
		ResponseObject<RamDTO> r = new ResponseObject<RamDTO>();
		try {
			r.setDati(ramS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllRam")
	public ResponseList<RamDTO> listAllRam(){
		ResponseList<RamDTO> r = new ResponseList<RamDTO>();
		try {
			r.setDati(ramS.listAll());
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listRams")
	public ResponseList<String> findAllRams() {
		ResponseList<String> r = new ResponseList<String>();
		try {
			r.setDati(ramS.listaDescrizioniRam());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
}
