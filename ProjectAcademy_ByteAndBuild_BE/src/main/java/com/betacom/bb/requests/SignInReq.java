package com.betacom.bb.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignInReq {
	private String user;
	private String pwd;
}
