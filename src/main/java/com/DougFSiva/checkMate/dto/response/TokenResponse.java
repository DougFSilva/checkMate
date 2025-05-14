package com.DougFSiva.checkMate.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TokenResponse {

	private String token;
	private String tipo;

	public TokenResponse(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}
}
