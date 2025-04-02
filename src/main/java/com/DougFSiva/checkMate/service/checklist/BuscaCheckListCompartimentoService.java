package com.DougFSiva.checkMate.service.checklist;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.CheckListCompartimentoResponse;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListCompartimentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaCheckListCompartimentoService {

	private final CheckListCompartimentoRepository repository;
	private final CheckListAmbienteRepository checkListAmbienteRepository;
	
	public CheckListCompartimentoResponse buscarPeloID(Long ID) {
		return new CheckListCompartimentoResponse(repository.findByIdOrElseThrow(ID));
	}
	
	public List<CheckListCompartimentoResponse> buscarPeloCheckListAmbiente(Long checkListAmbienteID) {
		CheckListAmbiente checkListAmbiente = checkListAmbienteRepository.findByIdOrElseThrow(checkListAmbienteID);
		return repository.findByCheckListAmbiente(checkListAmbiente)
				.stream()
				.map(CheckListCompartimentoResponse::new).toList();
	}
	
	public Page<CheckListCompartimentoResponse> buscarTodos(Pageable paginacao) {
		return repository.findAll(paginacao).map(CheckListCompartimentoResponse::new);
	}
}
