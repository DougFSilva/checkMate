package com.DougFSiva.checkMate.model;

import java.util.ArrayList;
import java.util.List;

import com.DougFSiva.checkMate.model.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
	
	@ManyToMany
	@JoinTable(
		name = "ambiente_guardiao", 
		joinColumns = @JoinColumn(name = "ambiente_id"), 
		inverseJoinColumns = @JoinColumn(name = "usuario_id")
	)
	private List<Usuario> guardioes = new ArrayList<>();
	
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
	
	public void adicionarGuardiao(Usuario usuario) {
		this.guardioes.add(usuario);
	}
	
	public void removerGuardiao(Usuario usuario) {
		this.guardioes.removeIf(guardiao -> guardiao.equals(usuario));
	}
	
	public String infoParaLog() {
		return String.format("(%d) %s", this.ID, this.nome);
	}

}
