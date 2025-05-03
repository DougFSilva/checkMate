package com.DougFSiva.checkMate.service.usuario;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaUsuarioService {

	private final static LoggerPadrao logger = new LoggerPadrao(DeletaUsuarioService.class);
	private final UsuarioRepository repository;

	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	public void deletar(Long ID) {
		Usuario usuario = repository.findByIdOrElseThrow(ID);
		repository.delete(usuario);
		logger.info(String.format("Usuário %s deletado", usuario.infoParaLog()));
	}

}
