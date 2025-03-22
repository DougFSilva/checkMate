package com.DougFSiva.checkMate.service.ocorrencia;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.OcorrenciaResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckList;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListRepository;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;
import com.DougFSiva.checkMate.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaOcorrenciaService {

	private final OcorrenciaRepository repository;
	private final AmbienteRepository ambienteRepository;
	private final CheckListRepository checkListRepository;
	private final UsuarioRepository usuarioRepository;

	public OcorrenciaResponse buscarPeloID(Long ID) {
		return new OcorrenciaResponse(repository.findByIdOrElseThrow(ID));
	}

	public Page<OcorrenciaResponse> buscarPelaDataHora(LocalDateTime dataInicial, LocalDateTime dataFinal,
			Pageable paginacao) {
		return repository
				.findByDataHoraBetween(dataInicial, dataFinal, paginacao)
				.map(OcorrenciaResponse::new);
	}
	
	public Page<OcorrenciaResponse> buscarPeloAmbiente(Long ambienteID, Pageable paginacao) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByItemCheckList_Compartimento_Ambiente(ambiente, paginacao)
				.map(OcorrenciaResponse::new);
	}
	
	public Page<OcorrenciaResponse> buscarPeloCheckList(Long checkListID, Pageable paginacao) {
		CheckList checkList = checkListRepository.findByIdOrElseThrow(checkListID);
		return repository.findByItemCheckList_CheckList(checkList, paginacao)
				.map(OcorrenciaResponse::new);
	}
	
	public Page<OcorrenciaResponse> buscarPeloResponsavelEncerramento(Long responsavelID, Pageable paginacao) {
		Usuario usuario = usuarioRepository.findByIdOrElseThrow(responsavelID);
		return repository.findByResponsavelEncerramento(usuario, paginacao)
				.map(OcorrenciaResponse::new);
	}
	
	public Page<OcorrenciaResponse> buscarTodas(Pageable paginacao) {
		return repository.findAll(paginacao)
				.map(OcorrenciaResponse::new);
	}

}
