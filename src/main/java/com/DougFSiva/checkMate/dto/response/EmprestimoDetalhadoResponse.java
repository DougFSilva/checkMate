package com.DougFSiva.checkMate.dto.response;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.Emprestimo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EmprestimoDetalhadoResponse {

	private Long ID;
	private ItemResumoResponse item;
	private UsuarioResumoResponse emprestador;
	private UsuarioResumoResponse solicitante;
	private UsuarioResumoResponse recebedor;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraEmprestimo;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraDevolucao;
	
	private boolean devolvido;
	
	public EmprestimoDetalhadoResponse(Emprestimo emprestimo) {
		this.ID = emprestimo.getID();
		this.item = new ItemResumoResponse(emprestimo.getItem());
		this.emprestador = new UsuarioResumoResponse(emprestimo.getEmprestador());
		this.solicitante = new UsuarioResumoResponse(emprestimo.getSolicitante());
		if (emprestimo.getRecebedor() != null) {
			this.recebedor = new UsuarioResumoResponse(emprestimo.getRecebedor());
		}
		this.dataHoraDevolucao = emprestimo.getDataHoraDevolucao();
		this.dataHoraEmprestimo = emprestimo.getDataHoraEmprestimo();
		this.devolvido = emprestimo.isDevolvido();
	}
}
