package com.DougFSiva.checkMate.service.checklist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.CheckListResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckList;
import com.DougFSiva.checkMate.model.checklist.CheckListStatus;
import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListRepository;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AbreCheckListService {

	private final CheckListRepository repository;
	private final AmbienteRepository ambienteRepository;
	private final ItemCheckListRepository itemCheckListRepository;
	private final ItemRepository itemRepository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;

	@Transactional
	public CheckListResponse abrir(Long ambienteID) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		Usuario usuario = buscaUsuarioAutenticado.buscar();
		CheckList checkList = new CheckList(ambiente, usuario);
		checkList.setStatus(CheckListStatus.ABERTO);
		CheckList checkListSalvo = repository.save(checkList);
		gerarListaDeItens(checkListSalvo, ambiente);
		return new CheckListResponse(checkListSalvo);
	}

	private void gerarListaDeItens(CheckList checkList, Ambiente ambiente) {
		List<ItemCheckList> itensCheckList = itemRepository
				.findByCompartimento_Ambiente(ambiente)
				.stream()
				.map(item -> new ItemCheckList(checkList, item))
				.collect(Collectors.toList());
		itemCheckListRepository.saveAll(itensCheckList);
		
	}

}
