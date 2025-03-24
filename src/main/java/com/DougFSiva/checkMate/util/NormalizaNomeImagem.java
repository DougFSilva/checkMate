package com.DougFSiva.checkMate.util;

public class NormalizaNomeImagem {

	public static String normalizar(String nome) {
	    return nome.toLowerCase() 
	               .replaceAll("\\s+", "_") 
	               .replaceAll("[^a-z0-9_.-]", ""); 
	}
}
