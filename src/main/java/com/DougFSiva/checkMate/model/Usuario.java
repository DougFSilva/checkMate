package com.DougFSiva.checkMate.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Usuario {

	private String ID;
	private String nome;
	private String CPF;
	private String email;
	private SenhaDeUsuario senha;
	private Boolean senhaAlterada;
	private Perfil perfil;
	private String foto;
	
	public Usuario(String nome, String CPF, String email, SenhaDeUsuario senha, Boolean senhaAlterada, Perfil perfil,
			String foto) {
		this.nome = nome;
		this.CPF = CPF;
		this.email = email;
		this.senha = senha;
		this.senhaAlterada = senhaAlterada;
		this.perfil = perfil;
		this.foto = foto;
	}
	
}
