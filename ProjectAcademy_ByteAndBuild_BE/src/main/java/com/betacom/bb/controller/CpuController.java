package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.CpuDTO;
import com.betacom.bb.requests.CpuReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ICpuServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rest/cpu")
public class CpuController {

	private ICpuServices cpuS;

	public CpuController(ICpuServices cpuS) {
		this.cpuS = cpuS;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) CpuReq req) {
		ResponseBase r = new ResponseBase();
		try {
			cpuS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  CpuReq req) {
		ResponseBase r = new ResponseBase();
		try {
			cpuS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  CpuReq req) {
		ResponseBase r = new ResponseBase();
		try {
			cpuS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getCpu")
	public ResponseObject<CpuDTO> getCpu(@RequestBody (required = true) Integer id ){
		ResponseObject<CpuDTO> r = new ResponseObject<CpuDTO>();
		try {
			r.setDati(cpuS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllCpu")
	public ResponseList<CpuDTO> listAllCpu(){
		ResponseList<CpuDTO> r = new ResponseList<CpuDTO>();
		try {
			r.setDati(cpuS.listAll());
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
