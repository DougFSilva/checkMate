package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.Emprestimo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmprestimoResumoResponse {

	private Long ID;
	private ItemResumoResponse item;
	private LocalDateTime dataHoraEmprestimo;
	private LocalDateTime dataHoraDevolucao;
	private boolean devolvido;
	
	public EmprestimoResumoResponse(Emprestimo emprestimo) {
		this.ID = emprestimo.getID();
		this.item = new ItemResumoResponse(emprestimo.getItem());
		this.dataHoraDevolucao = emprestimo.getDataHoraDevolucao();
		this.dataHoraEmprestimo = emprestimo.getDataHoraEmprestimo();
		this.devolvido = emprestimo.isDevolvido();
	}
}
