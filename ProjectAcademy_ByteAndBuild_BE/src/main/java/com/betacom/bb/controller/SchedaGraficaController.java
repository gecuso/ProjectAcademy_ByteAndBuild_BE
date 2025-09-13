package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.SchedaGraficaDTO;
import com.betacom.bb.requests.SchedaGraficaReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ISchedaGraficaServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/rest/schedaGrafica")
public class SchedaGraficaController {

	private ISchedaGraficaServices sgS;

	public SchedaGraficaController(ISchedaGraficaServices sgS) {
		this.sgS = sgS;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) SchedaGraficaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			sgS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true)  SchedaGraficaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			sgS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  SchedaGraficaReq req) {
		ResponseBase r = new ResponseBase();
		try {
			sgS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getSchedaGrafica")
	public ResponseObject<SchedaGraficaDTO> getSchedaGrafica(@RequestBody (required = true) Integer id ){
		ResponseObject<SchedaGraficaDTO> r = new ResponseObject<SchedaGraficaDTO>();
		try {
			r.setDati(sgS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllSchedaGrafica")
	public ResponseList<SchedaGraficaDTO> listAllSchedaGrafica(){
		ResponseList<SchedaGraficaDTO> r = new ResponseList<SchedaGraficaDTO>();
		try {
			r.setDati(sgS.listAll());
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
