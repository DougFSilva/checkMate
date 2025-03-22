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
public class TratamentoOcorrencia {

	private Long id;
	private Usuario autor;
	private String descricao;
	
	public TratamentoOcorrencia(Usuario autor, String descricao) {
		this.autor = autor;
		this.descricao = descricao;
	}
	
}
