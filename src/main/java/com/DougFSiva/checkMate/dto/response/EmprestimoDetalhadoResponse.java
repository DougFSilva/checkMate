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
	private UsuarioDetalhadoResponse emprestador;
	private UsuarioDetalhadoResponse solicitante;
	private UsuarioDetalhadoResponse recebedor;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraEmprestimo;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraDevolucao;
	
	private boolean devolvido;
	
	public EmprestimoDetalhadoResponse(Emprestimo emprestimo) {
		this.ID = emprestimo.getID();
		this.item = new ItemResumoResponse(emprestimo.getItem());
		this.emprestador = new UsuarioDetalhadoResponse(emprestimo.getEmprestador());
		this.solicitante = new UsuarioDetalhadoResponse(emprestimo.getSolicitante());
		if (emprestimo.getRecebedor() != null) {
			this.recebedor = new UsuarioDetalhadoResponse(emprestimo.getRecebedor());
		}
		this.dataHoraDevolucao = emprestimo.getDataHoraDevolucao();
		this.dataHoraEmprestimo = emprestimo.getDataHoraEmprestimo();
		this.devolvido = emprestimo.isDevolvido();
	}
}
