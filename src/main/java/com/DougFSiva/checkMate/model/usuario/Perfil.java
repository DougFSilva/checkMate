package com.DougFSiva.checkMate.model.usuario;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Perfil implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	private TipoPerfil tipo;

	@Override
	public String getAuthority() {
		return tipo.getDescricao();
	}
}
