package com.DougFSiva.checkMate.service.tarefas;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListCompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaCheckListsAntigosTask {
	
	@Value("${app.limpeza-checklist.retencao-dias}")
	private int retencaoDias;

	private final CheckListAmbienteRepository checkListAmbienteRepository;
	private final CheckListCompartimentoRepository checkListCompartimentoRepository;
	private final ItemCheckListRepository itemCheckListRepository;

	// Executa todo dia às 00:00
	@Transactional
	@Scheduled(cron = "0 0 1 * * ?")
	public void deletarChecklistsAntigos() {
		OffsetDateTime dataCorte = OffsetDateTime.now().minus(retencaoDias, ChronoUnit.DAYS);		
		List<CheckListAmbiente> checkListsAmbiente = checkListAmbienteRepository
				.findByDataHoraEncerramentoIsNotNullAndDataHoraEncerramentoBefore(dataCorte);
		itemCheckListRepository.deleteByCheckListCompartimento_CheckListAmbienteIn(checkListsAmbiente);
		checkListCompartimentoRepository.deleteByCheckListAmbienteIn(checkListsAmbiente);
		checkListAmbienteRepository.deleteAll(checkListsAmbiente);
	}

}
