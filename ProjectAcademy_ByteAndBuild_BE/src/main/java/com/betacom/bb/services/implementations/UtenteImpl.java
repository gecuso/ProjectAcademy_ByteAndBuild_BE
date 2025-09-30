package com.betacom.bb.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betacom.bb.dto.SignInDTO;
import com.betacom.bb.dto.UtenteDTO;
import com.betacom.bb.exception.AcademyException;
import com.betacom.bb.models.Utente;
import com.betacom.bb.repositories.IUtenteRepository;
import com.betacom.bb.requests.SignInReq;
import com.betacom.bb.requests.UtenteReq;
import com.betacom.bb.services.interfaces.IUtenteServices;
import com.betacom.bb.utilis.Roles;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteImpl implements IUtenteServices{
	private IUtenteRepository utenR;

	public UtenteImpl(IUtenteRepository utenR) {
		this.utenR = utenR;
	}

	
	@Override
	public void create(UtenteReq req) throws AcademyException {
		log.debug("Create:" + req);
		Optional<Utente> u = utenR.findByUserName(req.getUserName().trim());
		if (u.isPresent())
			throw new AcademyException("Username esistente");
		Utente ut = new Utente();
		ut.setUserName(req.getUserName());
		ut.setPwd(req.getPwd());
		ut.setCurrentpwd(req.getPwd());
		ut.setEmail(req.getEmail());
		ut.setIndirizzo(req.getIndirizzo());
		ut.setTelefono(req.getTelefono());
		ut.setRole(Roles.valueOf(req.getRole()));
		
		utenR.save(ut);
		
	}

	@Override
	public void update(UtenteReq req) throws AcademyException {
		log.debug("update :" + req);
		Optional<Utente> u = utenR.findById(req.getId());
		if (u.isEmpty())
			throw new AcademyException("Username inesistente");
		if(req.getCurrentpwd() == null || !req.getCurrentpwd().equals(u.get().getPwd())) {
			throw new AcademyException("Password corrente errata");
		}
		if (req.getUserName() != null)
		    u.get().setUserName(req.getUserName());
		if (req.getPwd() != null && !req.getPwd().isEmpty()) {
			u.get().setPwd(req.getPwd());
			u.get().setCurrentpwd(req.getPwd());
		}
		if (req.getRole() != null)
			u.get().setRole(Roles.valueOf(req.getRole()));
		if (req.getEmail() != null)
			u.get().setEmail(req.getEmail());
		if (req.getIndirizzo() != null)
			u.get().setIndirizzo(req.getIndirizzo());
		if (req.getTelefono() != null)
			u.get().setTelefono(req.getTelefono());

		utenR.save(u.get());
	}

	@Override
	public UtenteDTO remove(UtenteReq req) throws AcademyException {
		log.debug("remove:" + req);
		Optional<Utente> u = utenR.findById(req.getId());
		if (u.isEmpty())
			throw new AcademyException("Username inesistente");

		utenR.delete(u.get());
		return UtenteDTO.builder()
				.id(u.get().getId())
				.userName(u.get().getUserName())
				.pwd(u.get().getPwd())
				.currentpwd(u.get().getCurrentpwd())
				.email(u.get().getEmail())
				.indirizzo(u.get().getIndirizzo())
				.telefono(u.get().getTelefono())
				.role(u.get().getRole().toString())
				.build();
	}

	@Override
	public List<UtenteDTO> listAll() {
		log.debug("listAll");
		List<Utente> lU = utenR.findAll();
		return lU.stream()
				.map(u -> UtenteDTO.builder()
						.id(u.getId())
						.userName(u.getUserName())
						.pwd(u.getPwd())
						.email(u.getEmail())
						.indirizzo(u.getIndirizzo())
						.telefono(u.getTelefono())
						.role(u.getRole().toString())
						.build())
				.collect(Collectors.toList());
						
	}

	@Override
	public UtenteDTO findById(Integer id) throws AcademyException {
		log.debug("findById:" + id);
		Optional<Utente> u = utenR.findById(id);
		if (u.isEmpty())
			throw new AcademyException("Username inesistente");
		
		return UtenteDTO.builder()
				.id(u.get().getId())
				.userName(u.get().getUserName())
				.pwd(u.get().getPwd())
				.email(u.get().getEmail())
				.indirizzo(u.get().getIndirizzo())
				.telefono(u.get().getTelefono())
				.role(u.get().getRole().toString())
				.build();
	}


	@Override
	public SignInDTO signIn(SignInReq req) {
	    log.debug("signIn:" + req);
	    SignInDTO r = new SignInDTO();
	    Optional<Utente> u = utenR.findByUserNameAndPwd(req.getUser(), req.getPwd());
	    if (u.isEmpty()) {
	        r.setLogged(false);
	    } else {
	        Utente utenteEntity = u.get();
	        r.setId(utenteEntity.getId());
	        r.setLogged(true);
	        r.setRole(utenteEntity.getRole().toString());

	        UtenteDTO utenteDTO = UtenteDTO.builder()
	            .id(utenteEntity.getId())
	            .userName(utenteEntity.getUserName())
	            .pwd(utenteEntity.getPwd())
	            .currentpwd(utenteEntity.getCurrentpwd())
	            .email(utenteEntity.getEmail())
	            .indirizzo(utenteEntity.getIndirizzo())
	            .telefono(utenteEntity.getTelefono())
	            .role(utenteEntity.getRole().toString())
	            .build();

	        r.setUtente(utenteDTO);
	    }

	    return r;
	}



}
