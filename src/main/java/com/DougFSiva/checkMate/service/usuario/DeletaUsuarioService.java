package com.DougFSiva.checkMate.service.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComUsuarioException;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaUsuarioService {

	private final static LoggerPadrao logger = new LoggerPadrao(DeletaUsuarioService.class);
	private final UsuarioRepository repository;
	private final AmbienteRepository ambienteRepository;

	@Transactional
	public void deletar(Long ID) {
		Usuario usuario = repository.findByIdOrElseThrow(ID);
		if (ambienteRepository.existsByGuardioes(usuario)) {
			throw new ErroDeOperacaoComUsuarioException(
					"Não é possível excluir o usuário, pois há ambientes associados a ele!");
		}
		repository.delete(usuario);
		logger.info(String.format("Usuário %s deletado", usuario.infoParaLog()));
	}

}
