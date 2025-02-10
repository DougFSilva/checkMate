package com.DougFSiva.checkMate.service.usuario;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComUsuarioException;
import com.DougFSiva.checkMate.model.Usuario;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.service.ImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

@Service
public class DeleteUsuarioService {

	private final static LoggerPadrao logger = new LoggerPadrao(DeleteUsuarioService.class);
	private final UsuarioRepository repository;
	private final AmbienteRepository ambienteRepository;
	private final ImagemService imagemService;


	public DeleteUsuarioService(UsuarioRepository repository, AmbienteRepository ambienteRepository, ImagemService imagemService) {
		this.repository = repository;
		this.ambienteRepository = ambienteRepository;
		this.imagemService = imagemService;
	}

	public void deletar(Long ID) {
		Usuario usuario = repository.findByIdOrElseThrow(ID);
		if (ambienteRepository.existsByGuardioes(usuario)) {
			throw new ErroDeOperacaoComUsuarioException("Não é possível excluir o usuário, pois há ambientes associados a ele!");
		}
		repository.delete(usuario);
		imagemService.deletarFotoDeUsuario(usuario);
		logger.infoComUsuario(String.format("Usuário %s deletado", usuario.infoParaLog()));
	}
	
}
