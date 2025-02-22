package com.DougFSiva.checkMate.model.usuario;

import com.DougFSiva.checkMate.exception.SenhaDeUsuarioInvalidaException;

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
public class SenhaDeUsuario {

	private String senha;

	public SenhaDeUsuario(String senha, CodificadorDeSenha codificador) {

		if (!senha.matches("^(.*[0-9]).*$")) {
			throw new SenhaDeUsuarioInvalidaException("A senha precisa conter ao menos um dígito");
		}
		if (!senha.matches("^(?=.*[A-Z]).*$")) {
			throw new SenhaDeUsuarioInvalidaException("A senha precisa conter ao menos uma letra maiúscula");
		}
		if (!senha.matches("^(?=.*[a-z]).*$")) {
			throw new SenhaDeUsuarioInvalidaException("A senha precisa conter ao menos uma metra minúscula");
		}
		if (!senha.matches("^(?=.*[@#$%^&-+=().]).*$")) {
			throw new SenhaDeUsuarioInvalidaException("A senha precisa conter ao menos um caractere especial");
		}
		if (!senha.matches("^(?=\\S+$).*$")) {
			throw new SenhaDeUsuarioInvalidaException("A senha não pode conter espaços");
		}
		if (senha.length() < 6 || senha.length() > 20) {
			throw new SenhaDeUsuarioInvalidaException("A senha precisa ter no mínimo 6 e no máximo 20 caracteres");
		}
		this.senha = codificador.codificar(senha);
	}
}
