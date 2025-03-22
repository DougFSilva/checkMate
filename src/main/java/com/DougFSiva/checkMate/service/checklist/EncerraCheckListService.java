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
public class EncerraCheckListService {
	
	@Value("${mqtt.topico.root}")
	private String topicoRoot;

	private final CheckListRepository repository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	private final PublicadorMqtt publicadorMqtt;
	
	@Transactional
	public void encerrar(Long ID) {
		CheckList checkList = repository.findByIdOrElseThrow(ID);
		validarCheckListSaidaPreenchido(checkList);
		checkList.setDataHoraEncerramento(LocalDateTime.now());
		checkList.setResponsavelEncerramento(buscaUsuarioAutenticado.buscar());
		checkList.setStatus(CheckListStatus.ENCERRADO);
		repository.save(checkList);
		publicadorMqtt.enviarMensagem(
				topicoRoot + checkList.getAmbiente().getDescricao(), "ENCERRADO");
	}
	
	private void validarCheckListSaidaPreenchido(CheckList checkList) {
		if (checkList.getStatus() != CheckListStatus.SAIDA_PREENCHIDO) {
			throw new ErroDeOperacaoComCheckListException(
					"O check-list só pode ser encerrado se tiver status de preenchimento de saída");
		}
	}
}
