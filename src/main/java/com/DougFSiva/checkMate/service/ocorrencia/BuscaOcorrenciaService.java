package com.DougFSiva.checkMate.service.ocorrencia;

import java.time.LocalDateTime;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.OcorrenciaResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaOcorrenciaService {

	private final OcorrenciaRepository repository;
	private final AmbienteRepository ambienteRepository;

	@Cacheable(value = "ocorrencias", key = "'ocorrenciaID_' + #ID")
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	@Transactional(readOnly = true)
	public OcorrenciaResponse buscarPeloID(Long ID) {
		return new OcorrenciaResponse(repository.findByIdOrElseThrow(ID));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	@Transactional(readOnly = true)
	public Page<OcorrenciaResponse> buscarPelaDataHora(LocalDateTime dataInicial, LocalDateTime dataFinal,
			Pageable paginacao) {
		return repository
				.findByDataHoraBetween(dataInicial, dataFinal, paginacao)
				.map(OcorrenciaResponse::new);
	}
	
	@Cacheable(value = "ocorrencias", key = "'ocorrenciasPeloAmbiente_' + #ambienteID + '_pagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize")
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	@Transactional(readOnly = true)
	public Page<OcorrenciaResponse> buscarPeloAmbiente(Long ambienteID, Pageable paginacao) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByItemCheckList_CheckListCompartimento_CheckListAmbiente_Ambiente(ambiente, paginacao)
				.map(OcorrenciaResponse::new);
	}
	
	@Cacheable(value = "ocorrencias", key = "'todasOcorrenciasPagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize")
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	public Page<OcorrenciaResponse> buscarTodas(Pageable paginacao) {
		return repository.findAll(paginacao)
				.map(OcorrenciaResponse::new);
	}

}
