package com.DougFSiva.checkMate.service.checklist;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComCheckListException;
import com.DougFSiva.checkMate.model.PublicadorMqtt;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimentoStatus;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListCompartimentoRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncerraCheckListAmbienteService {
	
	private static final LoggerPadrao logger = new LoggerPadrao(EncerraCheckListAmbienteService.class);

	@Value("${mqtt.topico.root}")
	private String topicoRoot;

	private final CheckListAmbienteRepository repository;
	private final CheckListCompartimentoRepository checkListCompartimentoRepository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	private final PublicadorMqtt publicadorMqtt;
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	public void encerrar(Long ID) {
		CheckListAmbiente checkList = repository.findByIdOrElseThrow(ID);
		validarStatusCheckList(checkList);
		validarTodosCheckListsSaidaPreenchidos(checkList);
		checkList.setDataHoraEncerramento(LocalDateTime.now());
		checkList.setResponsavelEncerramento(buscaUsuarioAutenticado.buscar());
		checkList.setStatus(CheckListAmbienteStatus.ENCERRADO);
		repository.save(checkList);
		publicadorMqtt.enviarMensagem(
				topicoRoot + "/" + checkList.getAmbiente().getID(), "ENCERRADO");
		logger.info(String.format("Encerrado check-list para ambiente %s", 
				checkList.getAmbiente().infoParaLog()));

	}
	
	private void validarTodosCheckListsSaidaPreenchidos(CheckListAmbiente checkListAmbiente) {
		boolean existeNaoPreenchido = checkListCompartimentoRepository.findByCheckListAmbiente(checkListAmbiente)
		.stream()
		.anyMatch(checkListCompartimento -> 
			checkListCompartimento.getStatus() != CheckListCompartimentoStatus.SAIDA_PREENCHIDO);
		if (existeNaoPreenchido) {
			throw new ErroDeOperacaoComCheckListException(
					"O check-list do ambiente só pode ser encerrado se todos os check-lists"
					+ " de saída dos compartimentos estiverem concluídos");
		}
	}
	
	private void validarStatusCheckList(CheckListAmbiente checklist) {
		switch (checklist.getStatus()) {
			case LIBERADO: {
				return;
				
			}
			case ABERTO: {
				throw new ErroDeOperacaoComCheckListException(
						"O check-list do ambiente só pode ser encerrado se estiver liberado");
				
			}
			
			case ENCERRADO: {
				throw new ErroDeOperacaoComCheckListException(
						"O check-list do ambiente já foi encerrado");
				
			}
		}
	}
	
}
