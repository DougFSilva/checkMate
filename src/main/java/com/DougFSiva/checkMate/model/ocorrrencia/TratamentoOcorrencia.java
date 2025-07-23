package com.DougFSiva.checkMate.model.ocorrrencia;

import java.time.LocalDateTime;

import com.DougFSiva.checkMate.model.usuario.Usuario;

import jakarta.persistence.Entity;
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
@EqualsAndHashCode(of = {"ID"})
@ToString
public class TratamentoOcorrencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	private LocalDateTime dataHora;
	
	@ManyToOne
	@JoinColumn(name = "ocorrencia_id", nullable = false)
	private Ocorrencia ocorrencia;
	
	@ManyToOne
	@JoinColumn(name = "autor_id", nullable = false)
	private Usuario autor;
	
	private String descricao;
	
	public TratamentoOcorrencia(Ocorrencia ocorrencia, LocalDateTime dataHora, Usuario autor, String descricao) {
		this.ocorrencia = ocorrencia;
		this.dataHora = dataHora;
		this.autor = autor;
		this.descricao = descricao;
	}
	
}
