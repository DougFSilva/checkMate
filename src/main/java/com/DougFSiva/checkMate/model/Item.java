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
public class Item {

	private Long ID;
	private Compartimento compartimento;
	private String codigo;
	private String descricao;
	private Integer quantidade;
	private String imagem;
}
