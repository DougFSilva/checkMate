package com.DougFSiva.checkMate.service.checklist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.form.ItemCheckListForm;
import com.DougFSiva.checkMate.dto.form.PreencheCheckListForm;
import com.DougFSiva.checkMate.dto.response.CheckListResponse;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComCheckListException;
import com.DougFSiva.checkMate.model.checklist.CheckList;
import com.DougFSiva.checkMate.model.checklist.CheckListStatus;
import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.model.checklist.ItemCheckListStatus;
import com.DougFSiva.checkMate.repository.CheckListRepository;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;
import com.DougFSiva.checkMate.util.LoggerPadrao;

@Service
public class PreencheCheckListService {

	private static final LoggerPadrao logger = new LoggerPadrao(PreencheCheckListService.class);
	private final CheckListRepository repository;
	private final ItemCheckListRepository itemCheckListRepository;
	private final BuscaUsuarioAutenticado buscaUsuario;

	public PreencheCheckListService(CheckListRepository repository, ItemCheckListRepository itemCheckListRepository,
			BuscaUsuarioAutenticado buscaUsuario) {
		this.repository = repository;
		this.itemCheckListRepository = itemCheckListRepository;
		this.buscaUsuario = buscaUsuario;
	}

	public CheckListResponse preencher(PreencheCheckListForm form) {
		CheckList checkList = repository.findByIdOrElseThrow(form.ID());
		validarCheckListAberto(checkList);
		List<ItemCheckList> itens = itemCheckListRepository.findByCheckList(checkList);
		validarItens(form.itens());
		List<ItemCheckList> itensAtualizados = atualizarItens(itens, form.itens());
		itemCheckListRepository.saveAll(itensAtualizados);
		checkList.setDesvio(identificarDesvio(itensAtualizados));
		checkList.setDataHoraPreenchimentoEntrada(LocalDateTime.now());
		checkList.setObservacoes(form.observacao());
		checkList.setExecutorPreenchimentoEntrada(buscaUsuario.buscar().infoParaExecutorCheckList());
		checkList.setStatus(CheckListStatus.CHECKLIST_ENTRADA_PREENCHIDO);
		CheckList checkListSalvo = repository.save(checkList);
		logger.infoComUsuario(String.format("Preenchimento de entrada para check-list ID %s", checkList.getID()));
		return new CheckListResponse(checkListSalvo);

	}

	private void validarCheckListAberto(CheckList checkList) {
		if (checkList.getStatus() != CheckListStatus.ABERTO) {
			throw new ErroDeOperacaoComCheckListException(
					"O checklist de entrada só pode ser preenchido se estiver com o status 'Aberto'.");
		}
	}

	private void validarItens(List<ItemCheckListForm> itens) {
		boolean existeNaoVerificado = itens.stream()
		        .anyMatch(item -> item.status() == ItemCheckListStatus.NAO_VERIFICADO);

		    if (existeNaoVerificado) {
		        throw new ErroDeOperacaoComCheckListException(
		            "Não é possível validar o check-list pois há itens não verificados");
		    }
	}

	private List<ItemCheckList> atualizarItens(List<ItemCheckList> itens, List<ItemCheckListForm> itensForm) {
		Map<Long, ItemCheckListForm> mapaForm = itensForm.stream()
				.collect(Collectors.toMap(ItemCheckListForm::ID, form -> form));

		itens.forEach(item -> {
			ItemCheckListForm form = mapaForm.get(item.getID());
			if (form == null) {
				throw new ErroDeOperacaoComCheckListException(
						"Item com ID " + item.getID() + " não foi encontrado no checklist.");
			}
			item.setQuantidadeEncontrada(form.quantidadeEncontrada());
			item.setStatus(form.status());
			item.setAvariado(form.avariado());
			item.setObservacao(form.observacao());
		});

		return itens;
	}

	private boolean identificarDesvio(List<ItemCheckList> itens) {
		return itens.stream()
                .map(ItemCheckList::getStatus)
                .anyMatch(status -> status == ItemCheckListStatus.NAO_ENCONTRADO);
	}

}
