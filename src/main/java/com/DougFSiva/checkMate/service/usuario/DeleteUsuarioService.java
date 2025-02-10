package com.DougFSiva.checkMate.service.usuario;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComUsuarioException;
import com.DougFSiva.checkMate.model.Usuario;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

@Service
public class DeleteUsuarioService {

	private final static LoggerPadrao logger = new LoggerPadrao(DeleteUsuarioService.class);
	private final UsuarioRepository repository;
	private final AmbienteRepository ambienteRepository;


	public DeleteUsuarioService(UsuarioRepository repository, AmbienteRepository ambienteRepository) {
		this.repository = repository;
		this.ambienteRepository = ambienteRepository;
	}

	public void deletar(Long ID) {
		Usuario usuario = repository.findByIdOrElseThrow(ID);
		if (ambienteRepository.existsByGuardioes(usuario)) {
			throw new ErroDeOperacaoComUsuarioException("Não é possível excluir o usuário, pois há ambientes associados a ele!");
		}
		repository.delete(usuario);
		logger.infoComUsuario(String.format("Usuario com ID %d deletado", ID));
	}
	
}
