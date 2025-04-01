package com.DougFSiva.checkMate.model.ocorrrencia;

import java.time.LocalDateTime;
import java.util.List;

import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.model.usuario.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Ocorrencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	private LocalDateTime dataHora;
	private String emissor;
	
	@ManyToOne
	@JoinColumn(name = "item_checklist_id", nullable = false)
	private ItemCheckList itemCheckList;
	
	@ManyToOne
	@JoinColumn(name = "responsavel_encerramento_id", nullable = false)
	private Usuario responsavelEncerramento;
	
    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TratamentoOcorrencia> tratamentos;
    
	private boolean encerrada;
	
	public Ocorrencia(LocalDateTime dataHora, String emissor, ItemCheckList itemCheckList) {
		this.dataHora = dataHora;
		this.emissor = emissor;
		this.itemCheckList = itemCheckList;
	}
	
	public void addTratamento(TratamentoOcorrencia tratamento) {
		this.tratamentos.add(tratamento);
	}
	
}
