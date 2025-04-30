package com.DougFSiva.checkMate.model;

import jakarta.persistence.Column;
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
@EqualsAndHashCode(of = { "ID" })
@ToString
public class Compartimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	@ManyToOne
	@JoinColumn(name = "ambiente_id", nullable = false)
	private Ambiente ambiente;

	@Column(unique = true)
	private String codigo;
	private String nome;
	private String descricao;
	private String imagem;

	public Compartimento(Ambiente ambiente, String nome, String codigo, String descricao) {
		this.ambiente = ambiente;
		this.nome = nome;
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String infoParaLog() {
		return String.format("(%d) %s", this.ID, this.nome);
	}

}
