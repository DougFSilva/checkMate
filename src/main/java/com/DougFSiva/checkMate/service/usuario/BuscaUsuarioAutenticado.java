package com.DougFSiva.checkMate.service.usuario;

import org.springframework.security.core.context.SecurityContextHolder;

import com.DougFSiva.checkMate.model.usuario.Usuario;

public class BuscaUsuarioAutenticado {

	public Usuario buscar() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
