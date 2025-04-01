package com.DougFSiva.checkMate.model.checklist;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"ID"})
@ToString
public class CheckList {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@ManyToOne
    @JoinColumn(name = "ambiente_id", nullable = false)
	private Ambiente ambiente;
	
	private LocalDateTime dataHoraPreenchimentoEntrada;
	private LocalDateTime dataHoraLiberacao;
	private LocalDateTime dataHoraPreenchimentoSaida;
	private LocalDateTime dataHoraEncerramento;
	
	@ManyToOne
    @JoinColumn(name = "responsavel_abertura_id", nullable = false)
	private Usuario responsavelAbertura;
	
	private String executorPreenchimentoEntrada;
	
	@ManyToOne
    @JoinColumn(name = "responsavel_liberacao_id", nullable = false)
	private Usuario responsavelLiberacao;
	
	private String executorPreenchimentoSaida;
	
	@ManyToOne
    @JoinColumn(name = "responsavel_encerramento_id", nullable = false)
	private Usuario responsavelEncerramento;
	
	@Enumerated(EnumType.STRING)
	private CheckListStatus status;
	
	public CheckList(Ambiente ambiente, Usuario responsavelAbertura) {
		this.ambiente = ambiente;
		this.responsavelAbertura = responsavelAbertura;
	}
	
}
