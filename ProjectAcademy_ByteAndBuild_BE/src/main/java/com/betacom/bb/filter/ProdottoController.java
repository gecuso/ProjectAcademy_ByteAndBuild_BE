package com.betacom.bb.filter;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody; //occhio a non sbagliare con l'altro requestbody
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.bb.dto.ProdottoDTO;
import com.betacom.bb.response.ResponseBase;
import com.betacom.bb.response.ResponseList;
import com.betacom.bb.response.ResponseObject;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/rest/socio")
public class ProdottoController {

	private IProdottoServices prodS;
	
	public ProdottoController(IProdottoServices prodS) {
		this.prodS = prodS;
	}

	@GetMapping("/listAll")
	public ResponseList<ProdottoDTO> list() {
		ResponseList<ProdottoDTO> r = new ResponseList<ProdottoDTO>();
		try {
			r.setDati(prodS.listAll());
			r.setRc(true);

		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}

		return r;
	}

	@GetMapping("/list")
	public ResponseList<ProdottoDTO> listByFilter(
			@RequestParam(name = "descrizione", required = false) String descrizione,
			@RequestParam(name = "prezzo", required = false) Integer prezzo)
	{

		ResponseList<ProdottoDTO> r = new ResponseList<ProdottoDTO>();
		if (descrizione == null || descrizione.isBlank())
			descrizione = null;
		if (prezzo == null || prezzo.toString().isBlank())
			prezzo = null;

		try {
			r.setDati(prodS.list(descrizione, prezzo));
			r.setRc(true);

		} catch (Exception e) {
			r.setRc(false);
			r.setMsg(e.getMessage());
		}

		return r;
	}


}
