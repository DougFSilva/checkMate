package com.DougFSiva.checkMate.model;

import java.time.LocalDateTime;

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
public class CheckListFim {

	private LocalDateTime dataHora;
	private Usuario responsavel;
	private String executor;
	private String observacao;
}
