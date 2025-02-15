package com.DougFSiva.checkMate.model;

import java.time.LocalDateTime;

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
	private String executorPreenchimentoEntrada;
	private Usuario responsavelLiberacao;
	private String executorPreenchimentoSaida;
	private Usuario responsavelEncerramento;
	private CheckListStatus status;
	private String observacoes;
	private boolean desvio;
	
	public CheckList(Ambiente ambiente, LocalDateTime dataHoraPreenchimentoEntrada,
			LocalDateTime dataHoraLiberacao, LocalDateTime dataHoraPreenchimentoSaida,
			LocalDateTime dataHoraEncerramento, String executorPreenchimentoEntrada, Usuario responsavelLiberacao,
			String executorPreenchimentoSaida, Usuario responsavelEncerramento, CheckListStatus status,
			String observacoes, boolean desvio) {
		this.ambiente = ambiente;
		this.dataHoraPreenchimentoEntrada = dataHoraPreenchimentoEntrada;
		this.dataHoraLiberacao = dataHoraLiberacao;
		this.dataHoraPreenchimentoSaida = dataHoraPreenchimentoSaida;
		this.dataHoraEncerramento = dataHoraEncerramento;
		this.executorPreenchimentoEntrada = executorPreenchimentoEntrada;
		this.responsavelLiberacao = responsavelLiberacao;
		this.executorPreenchimentoSaida = executorPreenchimentoSaida;
		this.responsavelEncerramento = responsavelEncerramento;
		this.status = status;
		this.observacoes = observacoes;
		this.desvio = desvio;
	}
	
	public CheckList(Ambiente ambiente) {
		this.ambiente = ambiente;
		this.status = CheckListStatus.ABERTO;
	}
	
}
