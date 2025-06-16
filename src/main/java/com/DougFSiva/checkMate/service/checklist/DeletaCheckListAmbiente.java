package com.DougFSiva.checkMate.service.checklist;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComCheckListException;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;
import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListCompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaCheckListAmbiente {
	
	private static final LoggerPadrao logger = new LoggerPadrao(DeletaCheckListAmbiente.class);

	private final CheckListAmbienteRepository repository;
	private final ItemCheckListRepository  itemCheckListRepository;
	private final CheckListCompartimentoRepository checkListCompartimentoRepository;
	private final OcorrenciaRepository ocorrenciaRepository;
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	public void deletar(Long ID) {
		CheckListAmbiente checkList = repository.findByIdOrElseThrow(ID);
		validarCheckListAberto(checkList);
		List<CheckListCompartimento> checkListsCompartimento = checkListCompartimentoRepository.findByCheckListAmbiente(checkList);
		deletarOcorrencias(checkListsCompartimento);
		itemCheckListRepository.deleteByCheckListCompartimentoIn(checkListsCompartimento);
		checkListCompartimentoRepository.deleteAll(checkListsCompartimento);
		repository.delete(checkList);
		logger.info(String.format("Check-list %d deletado", checkList.getID()));
	}
	
	private void validarCheckListAberto(CheckListAmbiente checkList) {
		if (checkList.getStatus() != CheckListAmbienteStatus.ABERTO) {
			throw new ErroDeOperacaoComCheckListException("Somente um check-list aberto pode ser deletado");
		}
	}
	
	private void deletarOcorrencias(List<CheckListCompartimento> checklists) {
		checklists.forEach(checklist -> {
			List<ItemCheckList> itensCheckList = itemCheckListRepository.findByCheckListCompartimento(checklist);
			ocorrenciaRepository.deleteByItemCheckListIn(itensCheckList);
		});
	}
	
}
