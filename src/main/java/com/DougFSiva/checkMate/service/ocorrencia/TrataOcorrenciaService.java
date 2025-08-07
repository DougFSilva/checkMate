package com.DougFSiva.checkMate.service.ocorrencia;

import java.time.LocalDateTime;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.TrataOcorrenciaForm;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComOcorrenciaException;
import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;
import com.DougFSiva.checkMate.model.ocorrrencia.TratamentoOcorrencia;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;
import com.DougFSiva.checkMate.util.LoggerPadrao;
import com.DougFSiva.checkMate.websocket.TipoMensagemWebsocket;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrataOcorrenciaService {

	private static final LoggerPadrao logger = new LoggerPadrao(TrataOcorrenciaService.class);
	private final OcorrenciaRepository repository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	private final SimpMessagingTemplate websocket;

	@Transactional
	@PreAuthorize("isAuthenticated()")
	public void tratar(Long ID, TrataOcorrenciaForm form) {
		Ocorrencia ocorrencia = repository.findByIdOrElseThrow(ID);
		TratamentoOcorrencia tratamento = new TratamentoOcorrencia(
				ocorrencia, 
				LocalDateTime.now(),
				buscaUsuarioAutenticado.buscar(), 
				form.descricao());
		validarOcorrenciaAberta(ocorrencia);
		ocorrencia.addTratamento(tratamento);
		Ocorrencia ocorrenciaSalva = repository.save(ocorrencia);
		logger.info(String.format(
				"Adicionado tratamento à ocorrência %d", ocorrenciaSalva.getID()));
		websocket.convertAndSend("/topic/ocorrencias", TipoMensagemWebsocket.OCORRENCIA_TRATADA.toString());

	}
	
	private void validarOcorrenciaAberta(Ocorrencia ocorrencia) {
		if (ocorrencia.isEncerrada()) {
			throw new ErroDeOperacaoComOcorrenciaException("Só é possível tratar uma ocorrência aberta");
		}
	}
}
