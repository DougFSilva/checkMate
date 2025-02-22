package com.DougFSiva.checkMate.service.checklist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.CheckListResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.model.checklist.CheckList;
import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListRepository;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

@Service
public class AbreCheckListService {

	private static final LoggerPadrao logger = new LoggerPadrao(AbreCheckListService.class);
	private final CheckListRepository repository;
	private final AmbienteRepository ambienteRepository;
	private final ItemCheckListRepository itemCheckListRepository;
	private final ItemRepository itemRepository;
	
	public AbreCheckListService(CheckListRepository repository, AmbienteRepository ambienteRepository,
			ItemCheckListRepository itemCheckListRepository, ItemRepository itemRepository) {
		this.repository = repository;
		this.ambienteRepository = ambienteRepository;
		this.itemCheckListRepository = itemCheckListRepository;
		this.itemRepository = itemRepository;
	}

	public CheckListResponse abrir(Long ambienteID) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		CheckList checkList = new CheckList(ambiente);
		CheckList checkListSalvo = repository.save(checkList);
		gerarListaDeItens(checkListSalvo);
		logger.infoComUsuario(String.format(
				"Criado check-list %s para o ambiente %s", checkListSalvo.getID(), checkList.getAmbiente().infoParaLog()));
		return new CheckListResponse(checkListSalvo);
	}
	
	private void gerarListaDeItens(CheckList checkList) {
		List<Item> itens = itemRepository.findByCompartimento_Ambiente(checkList.getAmbiente());
		List<ItemCheckList> itensCheckList = itens.stream()
				.map(item -> new ItemCheckList(checkList, item))
				.collect(Collectors.toList());
		itemCheckListRepository.saveAll(itensCheckList);
	}

}
