package com.DougFSiva.checkMate.model;

import com.DougFSiva.checkMate.exception.EmailInvalidoException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = { "endereco" })
@ToString
public class Email {

	private String endereco;

	public Email(String endereco) {
		if (endereco == null || !endereco.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {

			throw new EmailInvalidoException(String.format("Email %s inválido!", endereco));
		}
		this.endereco = endereco;
	}
}