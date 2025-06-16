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
public class UsuarioDetalhadoResponse {

	private Long ID;
	private String nome;
	private String CPF;
	private String email;
	private boolean senhaAlterada;
	private String perfil;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataValidade;
	
	public UsuarioDetalhadoResponse(Usuario usuario) {
		this.ID = usuario.getID();
		this.nome = usuario.getNome();
		this.CPF = usuario.getCPF();
		this.email = usuario.getEmail();
		this.senhaAlterada = usuario.getSenhaAlterada();
		this.perfil = usuario.getPerfil().getTipo().name();
		this.dataValidade = usuario.getDataValidade();
	}
	
}
