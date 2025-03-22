package com.DougFSiva.checkMate.model.checklist;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.usuario.Usuario;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CheckList {

	private Long ID;
	private Ambiente ambiente;
	private LocalDateTime dataHoraPreenchimentoEntrada;
	private LocalDateTime dataHoraLiberacao;
	private LocalDateTime dataHoraPreenchimentoSaida;
	private LocalDateTime dataHoraEncerramento;
	private Usuario responsavelAbertura;
	private String executorPreenchimentoEntrada;
	private Usuario responsavelLiberacao;
	private String executorPreenchimentoSaida;
	private Usuario responsavelEncerramento;
	private CheckListStatus status;
	
	public CheckList(Ambiente ambiente, Usuario responsavelAbertura) {
		this.ambiente = ambiente;
		this.responsavelAbertura = responsavelAbertura;
	}
	
}
