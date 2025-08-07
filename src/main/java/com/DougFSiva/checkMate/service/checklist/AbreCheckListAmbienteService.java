package com.DougFSiva.checkMate.service.checklist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.CheckListAmbienteDetalhadoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;
import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListCompartimentoRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;
import com.DougFSiva.checkMate.util.LoggerPadrao;
import com.DougFSiva.checkMate.websocket.TipoMensagemWebsocket;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbreCheckListAmbienteService {

	private static final LoggerPadrao logger = new LoggerPadrao(AbreCheckListAmbienteService.class);

	private final CheckListAmbienteRepository checkListAmbienteRepository;
	private final CheckListCompartimentoRepository checkListCompartimentoRepository;
	private final AmbienteRepository ambienteRepository;
	private final CompartimentoRepository compartimentoRepository;
	private final ItemCheckListRepository itemCheckListRepository;
	private final ItemRepository itemRepository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	private final SimpMessagingTemplate websocket;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	public CheckListAmbienteDetalhadoResponse abrir(Long ambienteID) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		Usuario usuario = buscaUsuarioAutenticado.buscar();
		CheckListAmbiente checkList = new CheckListAmbiente(ambiente);
		checkList.setDataHoraAbertura(LocalDateTime.now());
		checkList.setResponsavelAbertura(usuario);
		CheckListAmbiente checkListSalvo = checkListAmbienteRepository.save(checkList);
		criarChecklistsPorCompartimento (checkListSalvo);
		logger.info(String.format("Aberto check-list para ambiente %s", ambiente.infoParaLog()));
		websocket.convertAndSend("/topic/checklistsambiente", TipoMensagemWebsocket.CHECKLIST_AMBIENTE_ABERTO.toString());
		return new CheckListAmbienteDetalhadoResponse(checkListSalvo);
	}
	
	private void criarChecklistsPorCompartimento (CheckListAmbiente checkListAmbiente) {
		List<CheckListCompartimento> checkListsCompartimento = compartimentoRepository
				.findByAmbiente(checkListAmbiente.getAmbiente())
				.stream()
				.map(compartimento -> new CheckListCompartimento(checkListAmbiente, compartimento))
				.collect(Collectors.toList());
		List<CheckListCompartimento> checkListsCompartimentoSalvos = checkListCompartimentoRepository.saveAll(checkListsCompartimento);
		checkListsCompartimentoSalvos.forEach(this::criarItensDoChecklist);
	}

	private void criarItensDoChecklist(CheckListCompartimento checkListCompartimento) {
		List<ItemCheckList> itensCheckList = itemRepository
				.findByCompartimentoAndVerificavel(checkListCompartimento.getCompartimento(), true)
				.stream()
				.map(item -> new ItemCheckList(checkListCompartimento, item))
				.collect(Collectors.toList());
		itemCheckListRepository.saveAll(itensCheckList);
	}
	

}
