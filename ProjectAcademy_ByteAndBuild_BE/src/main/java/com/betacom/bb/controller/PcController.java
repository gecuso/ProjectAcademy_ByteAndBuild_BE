package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.PcDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.requests.PcReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.IPcService;

@RestController
@RequestMapping("/rest/controller")
public class PcController {

	private IPcService pcS;
	

	public PcController(IPcService pcS) {
		this.pcS = pcS;
	}


	@PostMapping("/create")
	public ResponseBase create(@RequestBody (required = true)  PcReq req) {
		ResponseBase r = new ResponseBase();
		try {
			pcS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}


	@PostMapping("/update")
	public ResponseBase update(@RequestBody (required = true)  PcReq req) {
		ResponseBase r = new ResponseBase();
		try {
			pcS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@PostMapping("/delete")
	public ResponseBase delete(@RequestBody (required = true) PcReq req) throws AcademyException {
		ResponseBase r = new ResponseBase();
		try {
			pcS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@PostMapping("/getPc")
	public ResponseObject<PcDTO> getById(@RequestBody (required = true) Integer id) throws AcademyException {
		ResponseObject<PcDTO> r = new ResponseObject<PcDTO>();
		try {
			r.setDati(pcS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	@GetMapping("/listAll")
	public ResponseList<PcDTO> listAll() {
		ResponseList<PcDTO> r = new ResponseList<PcDTO>();
		try {
			r.setDati(pcS.listAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}

	
	@PostMapping("/controlloAlimentazione")
	public ResponseBase controlloAlimentazione(@RequestBody (required = true) PcReq req) throws AcademyException {
		ResponseBase r = new ResponseBase();
		try {
			pcS.controlloAlimentazione(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/controlloFormato")
	public ResponseBase controlloFormato(@RequestBody (required = true)String form1,@RequestBody (required = true) String form2) {
			ResponseBase r = new ResponseBase();
			try {
				pcS.controlloFormato(form1,form2);
				r.setRc(true);
			} catch (Exception e) {
				r.setRc(false);
				r.setMsg(e.getMessage());
			}
			return r;
	}
	@PostMapping("/controlloCompatibilita")
	public ResponseBase controlloCompatibilita(@RequestBody (required = true)String form1,@RequestBody (required = true) String form2) {
			ResponseBase r = new ResponseBase();
			try {
				pcS.controlloCompatibilita(form1,form2);
				r.setRc(true);
			} catch (Exception e) {
				r.setRc(false);
				r.setMsg(e.getMessage());
			}
			return r;
	}

	@PostMapping("/controlloQuantita")
	public ResponseBase controlloQuantita(@RequestBody (required = true)Integer n,@RequestBody (required = true)  PcReq pcReq) {
			ResponseBase r = new ResponseBase();
			try {
				pcS.controlloQuantita(n,pcReq);
				r.setRc(true);
			} catch (Exception e) {
				r.setRc(false);
				r.setMsg(e.getMessage());
			}
			return r;
	}

	@PostMapping("/riduciQuantita")
	public ResponseBase riduciQuantita(@RequestBody (required = true)Integer n,@RequestBody (required = true)  PcReq pcReq) {
			ResponseBase r = new ResponseBase();
			try {
				pcS.riduciQuantita(n,pcReq);
				r.setRc(true);
			} catch (Exception e) {
				r.setRc(false);
				r.setMsg(e.getMessage());
			}
			return r;
	}
	
	@PostMapping("/aumentaQuantita")
	public ResponseBase aumentaQuantita(@RequestBody (required = true)Integer n,@RequestBody (required = true)  PcReq pcReq) {
			ResponseBase r = new ResponseBase();
			try {
				pcS.aumentaQuantita(n,pcReq);
				r.setRc(true);
			} catch (Exception e) {
				r.setRc(false);
				r.setMsg(e.getMessage());
			}
			return r;
	}

}
