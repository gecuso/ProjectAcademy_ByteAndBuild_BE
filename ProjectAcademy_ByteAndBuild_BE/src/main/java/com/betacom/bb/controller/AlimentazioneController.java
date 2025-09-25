package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.AlimentazioneDTO;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.AlimentazioneReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.IAlimentazioneServices;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/rest/alimentazione")
@CrossOrigin(origins = "*")
@Log4j2
public class AlimentazioneController {

	private IAlimentazioneServices alimS;

	public AlimentazioneController(IAlimentazioneServices alimS) {
		this.alimS = alimS;
	}
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) AlimentazioneReq req) {
		ResponseBase r = new ResponseBase();
		try {
			alimS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		
		return r;
	}
	
	@PostMapping("/createAlimProd")
	public ResponseBase createAlimProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("controller alim : "+req);
		try {
			alimS.createAlimProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("/update")
	public ResponseBase update(@RequestBody (required = true)  AlimentazioneReq req) {
		ResponseBase r = new ResponseBase();
		try {
			alimS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true)  AlimentazioneReq req) {
		ResponseBase r = new ResponseBase();
		try {
			alimS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getAlimentazione")
	public ResponseObject<AlimentazioneDTO> getAlimentazione(@RequestParam (required = true) Integer id ){
		ResponseObject<AlimentazioneDTO> r = new ResponseObject<AlimentazioneDTO>();
		try {
			r.setDati(alimS.getById(id));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/listAllAlimentazione")
	public ResponseList<AlimentazioneDTO> listAllAlimentazione(){
		ResponseList<AlimentazioneDTO> r = new ResponseList<AlimentazioneDTO>();
		try {
			r.setDati(alimS.listAll());
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
