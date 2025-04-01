package com.DougFSiva.checkMate.service.ocorrencia;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.TrataOcorrenciaForm;
import com.DougFSiva.checkMate.dto.response.OcorrenciaResponse;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComOcorrenciaException;
import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;
import com.DougFSiva.checkMate.model.ocorrrencia.TratamentoOcorrencia;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrataOcorrenciaService {

	private static final LoggerPadrao logger = new LoggerPadrao(TrataOcorrenciaService.class);
	private final OcorrenciaRepository repository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;

	@Transactional
	public OcorrenciaResponse tratar(TrataOcorrenciaForm form) {
		Ocorrencia ocorrencia = repository.findByIdOrElseThrow(form.ocorrenciaID());
		TratamentoOcorrencia tratamento = new TratamentoOcorrencia(ocorrencia, buscaUsuarioAutenticado.buscar(), form.descricao());
		validarOcorrenciaAberta(ocorrencia);
		ocorrencia.addTratamento(tratamento);
		Ocorrencia ocorrenciaSalva = repository.save(ocorrencia);
		logger.infoComUsuario(String.format(
				"Adicionado tratamento à ocorrência com ID %d", ocorrenciaSalva.getID()));
		return new OcorrenciaResponse(ocorrenciaSalva);
	}
	
	private void validarOcorrenciaAberta(Ocorrencia ocorrencia) {
		if (ocorrencia.isEncerrada()) {
			throw new ErroDeOperacaoComOcorrenciaException("Só é possível tratar uma ocorrência aberta");
		}
	}
}
