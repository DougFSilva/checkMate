package com.DougFSiva.checkMate.model.checklist;

import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.model.Item;

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
@EqualsAndHashCode(of = {"ID"})
@ToString
public class ItemCheckList {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@ManyToOne
	@JoinColumn(name = "checklist_id", nullable = false)
	private CheckList checkList;
	
	@ManyToOne
	@JoinColumn(name = "compartimento_id", nullable = false)
	private Compartimento compartimento;
	
	private String descricao;
	private Integer quantidade;
	
	@Enumerated(EnumType.STRING)
	private ItemCheckListStatus statusEntrada;
	
	@Enumerated(EnumType.STRING)
	private ItemCheckListStatus statusSaida;
	
	private String observacaoEntrada;
	private String observacaoSaida;
	private String imagem;
	
	public ItemCheckList(CheckList checkList, Item item) {
		this.checkList = checkList;
		this.compartimento = item.getCompartimento();
		this.descricao = item.getDescricao();
		this.quantidade = item.getQuantidade();
		this.statusEntrada = ItemCheckListStatus.NAO_VERIFICADO;
		this.statusSaida = ItemCheckListStatus.NAO_VERIFICADO;
		this.imagem = item.getImagem();
	}
	
}
