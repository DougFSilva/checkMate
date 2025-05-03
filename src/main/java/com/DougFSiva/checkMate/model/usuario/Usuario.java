package com.DougFSiva.checkMate.model.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.DougFSiva.checkMate.model.Ambiente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	private String nome;
	private String CPF;
	
	@Column(unique = true)
	private String email;
	
	@Embedded
	private SenhaDeUsuario senha;
	
	private Boolean senhaAlterada;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "perfil_id", nullable = false)
	private Perfil perfil;
	
	private LocalDate dataValidade;
	
	@ManyToMany(mappedBy = "guardioes")
	private List<Ambiente> ambientes = new ArrayList<>();
	
	public Usuario(String nome, String CPF, String email, SenhaDeUsuario senha, Boolean senhaAlterada, Perfil perfil, LocalDate dataValidade) {
		this.nome = nome;
		this.CPF = CPF;
		this.email = email;
		this.senha = senha;
		this.senhaAlterada = senhaAlterada;
		this.perfil = perfil;
		this.dataValidade = dataValidade;
	}
	
	public String infoParaLog() {
		return String.format("(%d) %s", this.ID, this.nome);
	}
	
	public String infoParaExecutorCheckList() {
		return String.format("(%d) %s", this.ID, this.nome);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return this.perfil != null ? Collections.singletonList(this.perfil) : Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return this.senha.getSenha();
	}

	@Override
	public String getUsername() {
		return this.CPF;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return this.senhaAlterada;
	}
	
	
}
