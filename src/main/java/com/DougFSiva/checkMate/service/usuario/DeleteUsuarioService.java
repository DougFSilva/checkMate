package com.DougFSiva.checkMate.service.usuario;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComUsuarioException;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.service.imagem.DeletaImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteUsuarioService {

	private final static LoggerPadrao logger = new LoggerPadrao(DeleteUsuarioService.class);
	private final UsuarioRepository repository;
	private final AmbienteRepository ambienteRepository;
	private final DeletaImagemService imagemService;

	@Transactional
	public void deletar(Long ID) {
		Usuario usuario = repository.findByIdOrElseThrow(ID);
		if (ambienteRepository.existsByGuardioes(usuario)) {
			throw new ErroDeOperacaoComUsuarioException("Não é possível excluir o usuário, pois há ambientes associados a ele!");
		}
		repository.delete(usuario);
		imagemService.deletarFotoDeUsuario(usuario);
		logger.infoComUsuario(String.format("Usuário %s deletado", usuario.infoParaLog()));
	}
	
	private void deletarFoto(Usuario usuario) {
		if (!usuario.getFoto().equals(imagemService.buscarFotoDefaultDeUsuario())) {
			imagemService.deletarImagem(null);
		}
	}
	
}
