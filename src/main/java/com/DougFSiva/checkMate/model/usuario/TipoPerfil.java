package com.DougFSiva.checkMate.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum TipoPerfil {

	ADMIN(1, "ROLE_ADMIN"), 
	GESTOR(2, "ROLE_GESTOR"), 
	PROFESSOR(3, "ROLE_PROFESSOR"),
	FUNCIONARIO(4, "ROLE_FUNCIONARIO"), 
	ALUNO(5, "ROLE_ALUNO");

	private long codigo;
	private String descricao;

	public static TipoPerfil peloCodigo(Long codigo) {
		if (codigo != null) {
			for (TipoPerfil x : values()) {
				if (x.getCodigo() == codigo) {
					return x;
				}
			}
		}
		throw new IllegalArgumentException("Código de perfil inválido: " + codigo);
	}

}
