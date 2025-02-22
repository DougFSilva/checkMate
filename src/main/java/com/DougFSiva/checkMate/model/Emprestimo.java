package com.DougFSiva.checkMate.model;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.usuario.Usuario;

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
public class Emprestimo {

	private Long ID;
	private Item item;
	private Usuario receptor;
	private LocalDateTime dataHoraEmprestimo;
	private LocalDateTime dataHoraDevolucao;
}
