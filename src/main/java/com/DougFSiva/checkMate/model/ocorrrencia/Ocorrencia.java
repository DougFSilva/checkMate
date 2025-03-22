package com.DougFSiva.checkMate.model.ocorrrencia;

import java.time.LocalDateTime;
import java.util.List;

import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
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
public class Ocorrencia {

	private Long ID;
	private LocalDateTime dataHora;
	private String emissor;
	private ItemCheckList itemCheckList;
	private Usuario responsavelEncerramento;
	private List<TratamentoOcorrencia> tratamentos;
	private boolean encerrada;
	
	public Ocorrencia(LocalDateTime dataHora, String emissor, ItemCheckList itemCheckList) {
		this.dataHora = dataHora;
		this.emissor = emissor;
		this.itemCheckList = itemCheckList;
	}
	
}
