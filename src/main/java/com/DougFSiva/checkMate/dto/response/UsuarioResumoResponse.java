package com.DougFSiva.checkMate.dto.response;

import com.DougFSiva.checkMate.model.usuario.Usuario;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UsuarioResumoResponse {

	private Long ID;
	private String nome;
	private String email;
	private String perfil;
	
	public UsuarioResumoResponse(Usuario usuario) {
		this.ID = usuario.getID();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.perfil = usuario.getPerfil().getTipo().name();
	}
}
