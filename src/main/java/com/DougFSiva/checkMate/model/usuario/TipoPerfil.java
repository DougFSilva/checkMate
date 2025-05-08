package com.DougFSiva.checkMate.model.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum TipoPerfil {

	ADMIN(1, "ROLE_ADMIN", "ADMINISTRADOR"), 
	PROFESSOR(2, "ROLE_PROFESSOR", "PROFESSOR"),
	FUNCIONARIO(3, "ROLE_FUNCIONARIO", "FUNCIONARIO"), 
	ALUNO(4, "ROLE_ALUNO", "ALUNO");

	private long codigo;
	private String descricao;
	private String nome;
	
	public static TipoPerfil peloNome(String nome) {
        if (nome != null) {
            for (TipoPerfil x : values()) {
                if (x.getNome().equalsIgnoreCase(nome)) {
                    return x;
                }
            }
        }
        throw new IllegalArgumentException("Nome de perfil inválido: " + nome);
    }

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
