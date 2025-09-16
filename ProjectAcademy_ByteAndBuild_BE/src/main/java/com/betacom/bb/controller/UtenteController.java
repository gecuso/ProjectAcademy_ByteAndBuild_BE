package com.betacom.bb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.SignInDTO;
import com.betacom.bb.dto.UtenteDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.SignInReq;
import com.betacom.bb.requests.UtenteReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.IUtenteServices;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/rest/utente")
public class UtenteController {
	
	private IUtenteServices utS;

	public UtenteController(IUtenteServices utS) {
		super();
		this.utS = utS;
	}

	@PostMapping("/signin")
	public SignInDTO signin(@RequestBody (required = true) SignInReq req) {
		return utS.signIn(req);
	}
	
	
	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true) UtenteReq req) {
		log.debug("create :" + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			utS.create(req);
		} catch (AcademyException e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@PutMapping("/update")
	public ResponseBase update(@RequestBody (required = true) UtenteReq req) {
		log.debug("update :" + req);
		ResponseBase r = new ResponseBase();
		r.setRc(true);
		try {
			utS.update(req);
		} catch (AcademyException e) {
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@PostMapping("/remove")
	public ResponseBase remove(@RequestBody (required = true) UtenteReq req) {
		log.debug("remove :" + req);
		ResponseBase r = new ResponseBase();
		
		try {
			UtenteDTO u =  utS.remove(req);
			r.setRc(true);
			r.setMsg(u.getUserName());
		} catch (AcademyException e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/list")
	public ResponseList<UtenteDTO> listAll() {
		log.debug("list ");
		ResponseList<UtenteDTO> r = new ResponseList<UtenteDTO>();
		r.setRc(true);
		try{
			r.setDati(utS.listAll());
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}

	@GetMapping("/listSecurity")
	public List<UtenteDTO> listSecurity() {
		log.debug("listSecurity ");
		return utS.listAll();
	}
	
	@GetMapping("/listById")
	public ResponseObject<UtenteDTO> listById( Integer id) {
		log.debug("list:"+ id );
		ResponseObject<UtenteDTO> r = new ResponseObject<UtenteDTO>();
		r.setRc(true);
		try{
			r.setDati(utS.findById(id));
		} catch (Exception e) {
			log.error(e.getMessage());
			r.setMsg(e.getMessage());
			r.setRc(false);
		}
		return r;
	}
}
