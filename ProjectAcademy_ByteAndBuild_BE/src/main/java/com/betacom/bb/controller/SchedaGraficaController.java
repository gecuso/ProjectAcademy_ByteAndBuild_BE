package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.SchedaGraficaDTO;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.SchedaGraficaReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.ISchedaGraficaServices;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/rest/schedaGrafica")
@CrossOrigin(origins = "*")
@Log4j2
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
	
	@PostMapping("/createSchGrfProd")
	public ResponseBase createSchGrfProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("createSchGrfProd : "+req);
		try {
			sgS.createSchGrfProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	@PostMapping("/updateSchGrfProd")
	public ResponseBase updateSchGrfProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("updateSchGrfProd : "+req);
		try {
			sgS.updateSchGrfProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	@PostMapping("/deleteSchGrfProd")
	public ResponseBase deleteSchGrfProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("deleteSchGrfProd : "+req);
		try {
			sgS.deleteSchGrfProd(req);
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
	public ResponseObject<SchedaGraficaDTO> getSchedaGrafica(@RequestParam (required = true) Integer id ){
		ResponseObject<SchedaGraficaDTO> r = new ResponseObject<SchedaGraficaDTO>();
		try {
			r.setDati(sgS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/findByIdProd")
	public ResponseObject<SchedaGraficaDTO> findByIdProd(@RequestParam (required = true) Integer idProd ){
		ResponseObject<SchedaGraficaDTO> r = new ResponseObject<SchedaGraficaDTO>();
		try {
			r.setDati(sgS.findByIdProd(idProd));
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
