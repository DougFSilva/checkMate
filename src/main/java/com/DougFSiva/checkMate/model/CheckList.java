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
public class CheckList {

	private Long ID;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraFim;
	private Usuario responsavelInicio;
	private Usuario executorInicio;
	private Usuario responsavelFim;
	private Usuario executorFim;
	private Ambiente ambiente;
}
