package com.DougFSiva.checkMate.service.checklist;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComCheckListException;
import com.DougFSiva.checkMate.model.PublicadorMqtt;
import com.DougFSiva.checkMate.model.checklist.CheckList;
import com.DougFSiva.checkMate.model.checklist.CheckListStatus;
import com.DougFSiva.checkMate.repository.CheckListRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LiberaCheckListService {
	
	@Value("${mqtt.topico.root}")
	private String topicoRoot;

	private final CheckListRepository repository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	private final PublicadorMqtt publicadorMqtt;
	
	@Transactional
	public void liberarCheckList(Long ID) {
		CheckList checkList = repository.findByIdOrElseThrow(ID);
		validarCheckListEntradaPreenchido(checkList);
		checkList.setDataHoraLiberacao(LocalDateTime.now());
		checkList.setResponsavelLiberacao(buscaUsuarioAutenticado.buscar());
		checkList.setStatus(CheckListStatus.LIBERADO);
		repository.save(checkList);
		publicadorMqtt.enviarMensagem(
				topicoRoot + checkList.getAmbiente().getDescricao(), "LIBERADO");
		
	}
	
	private void validarCheckListEntradaPreenchido(CheckList checkList) {
		if (checkList.getStatus() != CheckListStatus.ENTRADA_PREENCHIDO) {
			throw new ErroDeOperacaoComCheckListException(
					"O check-list só pode ser liberado se tiver status de preenchimento de entrada");
		}
	}
}
