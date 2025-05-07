package com.DougFSiva.checkMate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "compartimento_id", nullable = false)
	private Compartimento compartimento;
	
	private String descricao;
	private Integer quantidade;
	private String imagem;
	private boolean verificavel;
	
	public Item(Compartimento compartimento, String descricao, Integer quantidade) {
		this.compartimento = compartimento;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.verificavel = true;
	}
	
	public String infoParaLog() {
		return String.format("(%d) %s", this.ID, this.descricao);
	}
}
