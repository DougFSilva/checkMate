package com.DougFSiva.checkMate.dto.response;

import java.time.OffsetDateTime;

import com.DougFSiva.checkMate.model.Emprestimo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmprestimoDetalhadoResponse {

	private Long ID;
	private ItemResumoResponse item;
	private UsuarioResponse emprestador;
	private UsuarioResponse solicitante;
	private UsuarioResponse recebedor;
	private OffsetDateTime dataHoraEmprestimo;
	private OffsetDateTime dataHoraDevolucao;
	
	private boolean devolvido;
	
	public EmprestimoDetalhadoResponse(Emprestimo emprestimo) {
		this.ID = emprestimo.getID();
		this.item = new ItemResumoResponse(emprestimo.getItem());
		this.emprestador = new UsuarioResponse(emprestimo.getEmprestador());
		this.solicitante = new UsuarioResponse(emprestimo.getSolicitante());
		if (emprestimo.getRecebedor() != null) {
			this.recebedor = new UsuarioResponse(emprestimo.getRecebedor());
		}
		this.dataHoraDevolucao = emprestimo.getDataHoraDevolucao();
		this.dataHoraEmprestimo = emprestimo.getDataHoraEmprestimo();
		this.devolvido = emprestimo.isDevolvido();
	}
}
