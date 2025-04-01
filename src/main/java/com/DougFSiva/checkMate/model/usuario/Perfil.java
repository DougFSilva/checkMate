package com.DougFSiva.checkMate.model.usuario;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Perfil implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoPerfil tipo;
	
	public Perfil(TipoPerfil tipo) {
		this.tipo = tipo;
	}

	@Override
	public String getAuthority() {
		return tipo.getDescricao();
	}
}
