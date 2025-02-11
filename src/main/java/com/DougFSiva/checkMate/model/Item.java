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
	private String descricao;
	private Integer quantidade;
	
	public Item(Compartimento compartimento, String descricao, Integer quantidade) {
		this.compartimento = compartimento;
		this.descricao = descricao;
		this.quantidade = quantidade;
	}
	
}
