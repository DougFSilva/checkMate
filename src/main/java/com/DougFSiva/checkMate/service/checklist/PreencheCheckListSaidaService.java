package com.DougFSiva.checkMate.service.checklist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.ItemCheckListForm;
import com.DougFSiva.checkMate.dto.form.PreencheCheckListForm;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComCheckListException;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimentoStatus;
import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.model.checklist.ItemCheckListStatus;
import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;
import com.DougFSiva.checkMate.repository.CheckListCompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreencheCheckListSaidaService {

	private final CheckListCompartimentoRepository repository;
	private final ItemCheckListRepository itemCheckListRepository;
	private final OcorrenciaRepository ocorrenciaRepository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;

	@Transactional
	@PreAuthorize("isAuthenticated()")
	public void preencher(PreencheCheckListForm form) {
		CheckListCompartimento checkList = repository.findByIdOrElseThrow(form.checkListCompartimentoID());
		validarCheckListAmbienteLiberado(checkList);
		validarCheckListNaoPreenchido(checkList);
		validarItens(form.itens());
		List<ItemCheckList> itens = itemCheckListRepository.findByCheckListCompartimento(checkList);
		List<ItemCheckList> itensAtualizados = atualizarItens(itens, form.itens());
		itemCheckListRepository.saveAll(itensAtualizados);
		gerarOcorrenciaSeAnormalidade(itensAtualizados);
		checkList.setDataHoraPreenchimentoSaida(LocalDateTime.now());
		checkList.setExecutorPreenchimentoSaida(buscaUsuarioAutenticado.buscar().infoParaExecutorCheckList());
		checkList.setStatus(CheckListCompartimentoStatus.SAIDA_PREENCHIDO);
		repository.save(checkList);

	}

	private void validarCheckListAmbienteLiberado(CheckListCompartimento checkList) {
		if (checkList.getCheckListAmbiente().getStatus() != CheckListAmbienteStatus.LIBERADO) {
			throw new ErroDeOperacaoComCheckListException(
					"O check-list de compartimento de saída só pode ser preenchido se "
					+ "o check-list do ambiente estiver com o status 'Liberado'.");
		}
	}
	
	private void validarCheckListNaoPreenchido(CheckListCompartimento checkList) {
		switch (checkList.getStatus()) {
			case ENTRADA_PREENCHIDO: {
				return;
			}
			case NAO_PREENCHIDO: {
				throw new ErroDeOperacaoComCheckListException(
						"O check-list de compartimento de entrada ainda não foi preenchido");
			}
			case SAIDA_PREENCHIDO: {
				throw new ErroDeOperacaoComCheckListException(
						"O check-list de compartimento de saída já foi preenchido");
			}
			
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
			item.setStatusSaida(form.status());
			item.setObservacaoSaida(form.observacao());
		});

		return itens;
	}
	
	private void gerarOcorrenciaSeAnormalidade(List<ItemCheckList> itens) {
		itens.stream()
		 	.filter(item -> item.getStatusSaida() != ItemCheckListStatus.OK)
		 	.forEach(this::criarOcorrencia);
	    
	}

	private void criarOcorrencia(ItemCheckList item) {
		if (!ocorrenciaRepository.existsByItemCheckList(item)) {
			Ocorrencia ocorrencia = new Ocorrencia(
			        LocalDateTime.now(),
			        buscaUsuarioAutenticado.buscar().infoParaExecutorCheckList(),
			        item
			    );
			ocorrenciaRepository.save(ocorrencia);
		}
	    
	}

}
