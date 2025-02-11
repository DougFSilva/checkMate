package com.DougFSiva.checkMate.model;

import java.util.ArrayList;
import java.util.List;

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
public class CheckList {

	private Long ID;
	private Ambiente ambiente;
	private List<ItemCheckList> itens = new ArrayList<>();
	private CheckListInicio checkListInicio;
	private CheckListFim checkListFim;
	private boolean desvio;
	
}
