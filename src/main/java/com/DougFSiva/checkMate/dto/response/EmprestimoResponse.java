package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.Emprestimo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmprestimoResponse {

	private Long ID;
	private ItemResponse item;
	private UsuarioResponse emprestador;
	private UsuarioResponse solicitante;
	private UsuarioResponse recebedor;
	private LocalDateTime dataHoraEmprestimo;
	private LocalDateTime dataHoraDevolucao;
	private boolean devolvido;
	
	public EmprestimoResponse(Emprestimo emprestimo) {
		this.ID = emprestimo.getID();
		this.item = new ItemResponse(emprestimo.getItem());
		this.emprestador = new UsuarioResponse(emprestimo.getEmprestador());
		this.recebedor = new UsuarioResponse(emprestimo.getRecebedor());
		this.solicitante = new UsuarioResponse(emprestimo.getSolicitante());
		this.dataHoraEmprestimo = emprestimo.getDataHoraEmprestimo();
		this.devolvido = emprestimo.isDevolvido();
	}
}
