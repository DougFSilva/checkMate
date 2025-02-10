package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.AmbienteResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.Usuario;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

@Service
public class AdicionaGuardiaoAoAmbienteService {

	private static final LoggerPadrao logger = new LoggerPadrao(AdicionaGuardiaoAoAmbienteService.class);
	private final AmbienteRepository repository;
	private final UsuarioRepository usuarioRepository;
	
	public AdicionaGuardiaoAoAmbienteService(AmbienteRepository repository, UsuarioRepository usuarioRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}
	
	public AmbienteResponse adicionar(Long IDUsuario, Long ambienteID) {
		Usuario usuario = usuarioRepository.findByIdOrElseThrow(IDUsuario);
		Ambiente ambiente = repository.findByIdOrElseThrow(ambienteID);
		ambiente.adicionarGuardiao(usuario);
		Ambiente ambienteSalvo = repository.save(ambiente);
		logger.infoComUsuario(String.format(
				"Adicionado guadião %s para o ambiente %s", usuario.infoParaLog(), ambiente.infoParaLog()));
		return new AmbienteResponse(ambienteSalvo);
	}
}
