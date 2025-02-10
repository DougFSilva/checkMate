package com.DougFSiva.checkMate.model;

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
public class Compartimento {

	private String ID;
	private Ambiente ambiente;
	private String codigo;
	private String nome;
	private String descricao;
	private String imagem;
	
	public Compartimento(Ambiente ambiente, String nome, String codigo, String descricao) {
		this.ambiente = ambiente;
		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public String infoParaLog() {
		return String.format("(%d) %s", this.ID, this.nome);
	}

	
}
