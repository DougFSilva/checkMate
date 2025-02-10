package com.DougFSiva.checkMate.dto.resposta;

import java.time.LocalDate;

import com.DougFSiva.checkMate.model.Usuario;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UsuarioResposta {

	private Long ID;
	private String nome;
	private String CPF;
	private String email;
	private String perfil;
	private LocalDate dataValidade;
	private String foto;
	
	public UsuarioResposta(Usuario usuario) {
		this.ID = usuario.getID();
		this.nome = usuario.getNome();
		this.CPF = usuario.getCPF();
		this.email = usuario.getEmail();
		this.perfil = usuario.getPerfil().getTipo().name();
		this.dataValidade = usuario.getDataValidade();
		this.foto = usuario.getFoto();
	}
	
}
