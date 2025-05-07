package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.Emprestimo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmprestimoResumoSemItemResponse {

	private Long ID;
	private LocalDateTime dataHoraEmprestimo;
	private LocalDateTime dataHoraDevolucao;
	private boolean devolvido;
	
	public EmprestimoResumoSemItemResponse(Emprestimo emprestimo) {
		this.ID = emprestimo.getID();
		this.dataHoraDevolucao = emprestimo.getDataHoraDevolucao();
		this.dataHoraEmprestimo = emprestimo.getDataHoraEmprestimo();
		this.devolvido = emprestimo.isDevolvido();
	}
}
