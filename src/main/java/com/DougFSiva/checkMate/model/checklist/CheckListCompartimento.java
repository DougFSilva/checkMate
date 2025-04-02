package com.DougFSiva.checkMate.model.checklist;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.Compartimento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = { "ID" })
@ToString
public class CheckListCompartimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@ManyToOne
	@JoinColumn(name = "checklist_ambiente_id", nullable = false)
	private CheckListAmbiente checkListAmbiente;
	
	@ManyToOne
	@JoinColumn(name = "compartimento_id", nullable = false)
	private Compartimento compartimento;
	
	private LocalDateTime dataHoraPreenchimentoEntrada;
	private LocalDateTime dataHoraPreenchimentoSaida;
	private String executorPreenchimentoEntrada;
	private String executorPreenchimentoSaida;
	
	@Enumerated(EnumType.STRING)
	private CheckListCompartimentoStatus status;
	
	public CheckListCompartimento(CheckListAmbiente checkListAmbiente, Compartimento compartimento) {
		this.checkListAmbiente = checkListAmbiente;
		this.compartimento = compartimento;
		this.status = CheckListCompartimentoStatus.NAO_PREENCHIDO;
	}
	
}
