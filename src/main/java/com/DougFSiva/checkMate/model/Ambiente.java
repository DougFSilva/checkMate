package com.DougFSiva.checkMate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Ambiente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(unique = true)
	private String nome;
	private String descricao;
	private String localizacao;
	private String imagem;
	
	public Ambiente(String nome, String descricao, String localizacao) {
		this.nome = nome;
		this.descricao = descricao;
		this.localizacao = localizacao;
	}
	
	public String infoParaLog() {
		return String.format("(%d) %s", this.ID, this.nome);
	}

}
