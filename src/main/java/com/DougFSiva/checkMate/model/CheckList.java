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
public class CheckList {

	private Long ID;
	private Ambiente ambiente;
	private CheckListInicio checkListInicio;
	private CheckListFim checkListFim;
}
