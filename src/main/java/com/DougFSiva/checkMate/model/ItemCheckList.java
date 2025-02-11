package com.DougFSiva.checkMate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemCheckList {

	private Long ID;
	private CheckList checkList;
	private Item item;
	private Integer quantidadeEncontrada;
	private boolean statusInicio;
	private boolean statusFim;
	private String observacao;
	
}
