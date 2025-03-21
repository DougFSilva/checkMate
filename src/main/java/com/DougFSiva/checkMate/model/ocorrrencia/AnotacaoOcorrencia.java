package com.DougFSiva.checkMate.model.ocorrrencia;

import com.DougFSiva.checkMate.model.usuario.Usuario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AnotacaoOcorrencia {

	private Long id;
	private Ocorrencia ocorrencia;
	private Usuario autor;
	private String descricao;
	
	public AnotacaoOcorrencia(Ocorrencia ocorrencia, Usuario autor, String descricao) {
		this.ocorrencia = ocorrencia;
		this.autor = autor;
		this.descricao = descricao;
	}
	
}
