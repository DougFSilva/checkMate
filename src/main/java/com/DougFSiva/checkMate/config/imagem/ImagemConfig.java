package com.DougFSiva.checkMate.config.imagem;

public class ImagemConfig {

	public static final String PASTA_IMAGEM_COMPARTIMENTO = "compartimentos"; 
	public static final String PASTA_IMAGEM_AMBIENTE = "ambientes";

	public static String getNomeImagemCompartimentoDefault() {
		return PASTA_IMAGEM_COMPARTIMENTO + "/compartimento-default.jpg";
	}
	
	public static String getNomeImagemAmbienteDefault() {
		return PASTA_IMAGEM_AMBIENTE + "/ambiente-default.jpg";
	}
}
