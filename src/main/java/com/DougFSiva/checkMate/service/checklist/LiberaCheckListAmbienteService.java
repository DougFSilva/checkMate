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
public class LiberaCheckListAmbienteService {
	
	private static final LoggerPadrao logger = new LoggerPadrao(LiberaCheckListAmbienteService.class);
	
	@Value("${mqtt.topico.root}")
	private String topicoRoot;

	private final CheckListAmbienteRepository repository;
	private final CheckListCompartimentoRepository checkListCompartimentoRepository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	private final PublicadorMqtt publicadorMqtt;
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'FUNCIONARIO')")
	public void liberarCheckList(Long ID) {
		CheckListAmbiente checkList = repository.findByIdOrElseThrow(ID);
		validaCheckListAberto(checkList);
		validarCheckListEntradaPreenchido(checkList);
		checkList.setDataHoraLiberacao(LocalDateTime.now());
		checkList.setResponsavelLiberacao(buscaUsuarioAutenticado.buscar());
		checkList.setStatus(CheckListAmbienteStatus.LIBERADO);
		repository.save(checkList);
		publicadorMqtt.enviarMensagem(
				topicoRoot + "/" + checkList.getAmbiente().getID(), "LIBERADO");
		logger.info(String.format("Liberado check-list para ambiente %s", 
				checkList.getAmbiente().infoParaLog()));
	}
	
	private void validarCheckListEntradaPreenchido(CheckListAmbiente checkListAmbiente) {
		boolean existeNaoPreenchido = checkListCompartimentoRepository.findByCheckListAmbiente(checkListAmbiente)
		.stream()
		.anyMatch(checkListCompartimento -> 
			checkListCompartimento.getStatus() != CheckListCompartimentoStatus.ENTRADA_PREENCHIDO);
		if (existeNaoPreenchido) {
			throw new ErroDeOperacaoComCheckListException(
					"O check-list do ambiente só pode ser liberado se todos os check-lists"
					+ " de entrada dos compartimentos estiverem concluídos");
		}
	}
	
	private void validaCheckListAberto(CheckListAmbiente checklist) {
		switch (checklist.getStatus()) {
			case ABERTO: {
				return;
			}	
			case LIBERADO: {
				throw new ErroDeOperacaoComCheckListException(
						"O check-list do ambiente já foi liberado");
			}
			case ENCERRADO: {
				throw new ErroDeOperacaoComCheckListException(
						"Não é possível liberar o checklist de ambiente pois o mesmo já foi encerrado");
			}
		
		}
	}
}
