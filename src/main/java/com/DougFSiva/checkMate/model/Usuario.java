package com.DougFSiva.checkMate.model;

import java.util.ArrayList;
import java.util.List;

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
	private Email email;
	private SenhaDeUsuario senha;
	private Boolean senhaAlterada;
	private List<Perfil> perfis = new ArrayList<Perfil>();
	private String foto;
}
