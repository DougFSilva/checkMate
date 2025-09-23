package com.DougFSiva.checkMate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthResponse {

	private String token;
	private String tipo;
	private boolean senhaAlterada;

}
