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
	private Usuario emprestador;
	private Usuario solicitante;
	private Usuario recebedor;
	private LocalDateTime dataHoraEmprestimo;
	private LocalDateTime dataHoraDevolucao;
	private boolean devolvido;
	
	public Emprestimo(Item item, Usuario emprestador, Usuario solicitante, LocalDateTime dataHoraEmprestimo) {
		this.item = item;
		this.emprestador = emprestador;
		this.solicitante = solicitante;
		this.dataHoraEmprestimo = dataHoraEmprestimo;
		this.devolvido = false;
	}
	
	
}
