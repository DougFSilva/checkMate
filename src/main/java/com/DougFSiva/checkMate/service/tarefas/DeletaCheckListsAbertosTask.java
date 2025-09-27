package com.DougFSiva.checkMate.service.tarefas;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListCompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaCheckListsAbertosTask {
	
	private final CheckListAmbienteRepository checkListAmbienteRepository;
	private final CheckListCompartimentoRepository checkListCompartimentoRepository;
	private final ItemCheckListRepository itemCheckListRepository;

	// Executa todo dia às 00:00
	@Transactional
	@Scheduled(cron = "0 0 1 * * ?")
	public void deletarChecklistsAntigos() {
		List<CheckListAmbiente> checkListsAmbiente = checkListAmbienteRepository
				.findByStatus(CheckListAmbienteStatus.ABERTO);
		itemCheckListRepository.deleteByCheckListCompartimento_CheckListAmbienteIn(checkListsAmbiente);
		checkListCompartimentoRepository.deleteByCheckListAmbienteIn(checkListsAmbiente);
		checkListAmbienteRepository.deleteAll(checkListsAmbiente);
	}

}
