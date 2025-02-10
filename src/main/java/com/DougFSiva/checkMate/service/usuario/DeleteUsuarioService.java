package com.DougFSiva.checkMate.service.usuario;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.repository.UsuarioRepository;

@Service
public class DeleteUsuarioService {

	private final UsuarioRepository repository;

	public DeleteUsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public void deletar(Long ID) {
		repository.findById(ID)
	}
	
}
