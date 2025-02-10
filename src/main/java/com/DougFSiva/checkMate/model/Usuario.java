package com.DougFSiva.checkMate.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String ID;
	private String nome;
	private String CPF;
	private String email;
	private SenhaDeUsuario senha;
	private Boolean senhaAlterada;
	private Perfil perfil;
	private LocalDate dataValidade;
	private String foto;
	
	public Usuario(String nome, String CPF, String email, SenhaDeUsuario senha, Boolean senhaAlterada, Perfil perfil, LocalDate dataValidade,
			String foto) {
		this.nome = nome;
		this.CPF = CPF;
		this.email = email;
		this.senha = senha;
		this.senhaAlterada = senhaAlterada;
		this.perfil = perfil;
		this.dataValidade = dataValidade;
		this.foto = foto;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return this.perfil != null ? Collections.singletonList(this.perfil) : Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return this.senha.getSenha();
	}

	@Override
	public String getUsername() {
		return this.CPF;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return this.senhaAlterada;
	}
	
}
