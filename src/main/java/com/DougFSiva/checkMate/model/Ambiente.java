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
public class Ambiente {

	private Long ID;
	private String nome;
	private String descricao;
	private String localizacao;
	private String imagem;
	
}
