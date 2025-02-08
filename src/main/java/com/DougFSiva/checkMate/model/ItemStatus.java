package com.DougFSiva.checkMate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemStatus {

	private Long ID;
	private Item item;
	private boolean status;
	
}
