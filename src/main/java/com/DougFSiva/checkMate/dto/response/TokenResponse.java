package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.usuario.Perfil;
import com.DougFSiva.checkMate.model.usuario.TipoPerfil;
import com.DougFSiva.checkMate.model.usuario.Usuario;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TokenResponse {

	private String token;
	private String tipo;
	private UsuarioResponse usuario;
	private TipoPerfil perfil;

	public TokenResponse(String token, String tipo, Usuario usuario, Perfil perfil) {
		this.token = token;
		this.tipo = tipo;
		this.usuario = new UsuarioResponse(usuario);
		this.perfil = perfil.getTipo();
	}
}
