package com.betacom.bb.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.MonitorDTO;
import com.betacom.bb.requests.GeneralReq;
import com.betacom.bb.requests.MonitorReq;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;
import com.betacom.bb.services.interfaces.IMonitorService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/rest/monitor")
@CrossOrigin(origins = "*")
@Log4j2
public class MonitorController {
	
	private IMonitorService monS;

	public MonitorController(IMonitorService monS) {
		this.monS = monS;
	}
	
	////////////////////////////////

	@PostMapping("create")
	public ResponseBase create(@RequestBody (required = true) MonitorReq req) {		
		ResponseBase r = new ResponseBase();
		try {
			monS.create(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PostMapping("/createMonitorProd")
	public ResponseBase createMonitorProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("createMonitorProd : "+req);
		try {
			monS.createMonitorProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	@PutMapping("/updateMonitorProd")
	public ResponseBase updateMonitorProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("updateMonitorProd : "+req);
		try {
			monS.updateMonitorProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	@PutMapping("/deleteMonitorProd")
	public ResponseBase deleteMonitorProd(@RequestBody (required = true) GeneralReq req) {
		ResponseBase r = new ResponseBase();
		log.debug("deleteMonitorProd : "+req);
		try {
			monS.deleteMonitorProd(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@PutMapping("update")
	public ResponseBase update(@RequestBody (required = true) MonitorReq req) {
		ResponseBase r = new ResponseBase();
		try {
			monS.update(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@DeleteMapping("delete")
	public ResponseBase delete(@RequestBody (required = true) MonitorReq req) {
		ResponseBase r = new ResponseBase();
		try {
			monS.delete(req);
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	////////////////////////////////

	@GetMapping("/findAll")
	public ResponseList<MonitorDTO> findAll() {
		ResponseList<MonitorDTO> r = new ResponseList<MonitorDTO>();
		try {
			r.setDati(monS.findAll());
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/findByIdProd")
	public ResponseObject<MonitorDTO> findByIdProd(@RequestParam (required = true) Integer idProd ){
		ResponseObject<MonitorDTO> r = new ResponseObject<MonitorDTO>();
		try {
			r.setDati(monS.findByIdProd(idProd));
		}catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	@GetMapping("/getById")
	public ResponseObject<MonitorDTO> getById(@RequestParam (required = true)  Integer id) {
		ResponseObject<MonitorDTO> r = new ResponseObject<MonitorDTO>();
		try {
			r.setDati(monS.getById(id));
			r.setRc(true);
		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}
		return r;	
	}
		
}
