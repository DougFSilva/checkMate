package com.DougFSiva.checkMate.service.checklist;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
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
	public void encerrar(Long ID) {
		CheckListAmbiente checkList = repository.findByIdOrElseThrow(ID);
		validarCheckListSaidaPreenchido(checkList);
		checkList.setDataHoraEncerramento(LocalDateTime.now());
		checkList.setResponsavelEncerramento(buscaUsuarioAutenticado.buscar());
		checkList.setStatus(CheckListAmbienteStatus.ENCERRADO);
		repository.save(checkList);
		publicadorMqtt.enviarMensagem(
				topicoRoot + "/" + checkList.getAmbiente().getDescricao(), "ENCERRADO");
		logger.infoComUsuario(String.format("Encerrado check-list para ambiente %s", 
				checkList.getAmbiente().infoParaLog()));

	}
	
	private void validarCheckListSaidaPreenchido(CheckListAmbiente checkListAmbiente) {
		boolean existeNaoPreenchido = checkListCompartimentoRepository.findByCheckListAmbiente(checkListAmbiente)
		.stream()
		.anyMatch(checkListCompartimento -> 
			checkListCompartimento.getStatus() != CheckListCompartimentoStatus.SAIDA_PREENCHIDO);
		if (existeNaoPreenchido) {
			throw new ErroDeOperacaoComCheckListException(
					"O check-list do ambiente só pode ser encerrado se todos os check-lists "
					+ " de saída dos compartimentos estiverem concluídos");
		}
	}
	
}
