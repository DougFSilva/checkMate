package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDate;

import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UsuarioResponse {

	private Long ID;
	private String nome;
	private String CPF;
	private String email;
	private boolean senhaAlterada;
	private String perfil;
	private LocalDate dataValidade;
	
	public UsuarioResponse(Usuario usuario) {
		this.ID = usuario.getID();
		this.nome = usuario.getNome();
		this.CPF = usuario.getCPF();
		this.email = usuario.getEmail();
		this.senhaAlterada = usuario.getSenhaAlterada();
		this.perfil = usuario.getPerfil().getTipo().name();
		this.dataValidade = usuario.getDataValidade();
	}
	
}
