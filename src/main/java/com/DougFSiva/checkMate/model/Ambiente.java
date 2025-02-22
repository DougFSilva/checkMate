package com.DougFSiva.checkMate.model;

import java.util.ArrayList;
import java.util.List;

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
public class Ambiente {

	private Long ID;
	private List<Usuario> guardioes = new ArrayList<>();
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
