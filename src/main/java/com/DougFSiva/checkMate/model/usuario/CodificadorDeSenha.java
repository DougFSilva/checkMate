package com.DougFSiva.checkMate.model.usuario;

public interface CodificadorDeSenha {

	String codificar(String senha);
	
	boolean comparar(String senha, String senhaCodificada);
}
