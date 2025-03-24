package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.AmbienteResponse;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComAmbienteException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemoveGuardiaoDoAmbiente {
	
	private static final LoggerPadrao logger = new LoggerPadrao(RemoveGuardiaoDoAmbiente.class);
	private final AmbienteRepository repository;
	private final UsuarioRepository usuarioRepository;
	
	@Transactional
	public AmbienteResponse remover(Long IDUsuario, Long ambienteID) {
		Usuario usuario = usuarioRepository.findByIdOrElseThrow(IDUsuario);
		Ambiente ambiente = repository.findByIdOrElseThrow(ambienteID);
		if (!ambiente.getGuardioes().contains(usuario)) {
			throw new ErroDeOperacaoComAmbienteException(String.format(
					"O ambiente %s não contém o guardião %s", ambiente.infoParaLog(), usuario.infoParaLog()));
		}
		ambiente.removerGuardiao(usuario);
		Ambiente ambienteSalvo = repository.save(ambiente);
		logger.infoComUsuario(String.format(
				"Removido guardião %s do ambiente %s", usuario.infoParaLog(), ambiente.infoParaLog()));
		return new AmbienteResponse(ambienteSalvo);
	}
}
