package com.DougFSiva.checkMate.service.ocorrencia;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComOcorrenciaException;
import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;
import com.DougFSiva.checkMate.util.LoggerPadrao;
import com.DougFSiva.checkMate.websocket.TipoMensagemWebsocket;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncerraOcorrenciaService {

	private static final LoggerPadrao logger = new LoggerPadrao(EncerraOcorrenciaService.class);
	private final OcorrenciaRepository repository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	private final SimpMessagingTemplate websocket;
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void encerrar(Long id) {
		Ocorrencia ocorrencia = repository.findByIdOrElseThrow(id);
		validarOcorrenciaTratada(ocorrencia);
		ocorrencia.setEncerrada(true);
		ocorrencia.setResponsavelEncerramento(buscaUsuarioAutenticado.buscar());
		repository.save(ocorrencia);
		logger.info(String.format("Ocorrência %d encerrada", id));
		websocket.convertAndSend("/topic/ocorrencias", TipoMensagemWebsocket.OCORRENCIA_ENCERRADA.toString());
	}
	
	private void validarOcorrenciaTratada(Ocorrencia ocorrencia) {
		if (ocorrencia.getTratamentos().size() < 1) {
			throw new ErroDeOperacaoComOcorrenciaException("Só é possível encerrar uma ocorrência que foi tratada");
		}
	}
}
