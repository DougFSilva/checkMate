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
public class CheckListInicio {

	private LocalDateTime dataHora;
	private Usuario responsavel;
	private Usuario executor;
	private String observacao;
}
