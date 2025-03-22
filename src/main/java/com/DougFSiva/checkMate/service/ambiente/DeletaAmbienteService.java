package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComAmbienteException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.service.ImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaAmbienteService {

	private static final LoggerPadrao logger = new LoggerPadrao(DeletaAmbienteService.class);
	private final AmbienteRepository repository;
	private final CheckListRepository checkListRepository;
	private final CompartimentoRepository compartimentoRepository;
	private final ImagemService imagemService;
	
	public void deletar(Long ID) {
		Ambiente ambiente = repository.findByIdOrElseThrow(ID);
		if (checkListRepository.existsByAmbiente(ambiente)) {
			throw new ErroDeOperacaoComAmbienteException(
					"Não é possível deletar ambiente, pois existema checklists associados a ele!");
		}
		if (compartimentoRepository.existsByAmbiente(ambiente)) {
			throw new ErroDeOperacaoComAmbienteException(
					"Não é possível deletar ambiente, pois existema compartimentos associados a ele!");
		}
		repository.delete(ambiente);
		imagemService.deletarImagemDeAmbiente(ambiente);
		logger.infoComUsuario(String.format("Deletado Ambiente %s", ambiente.infoParaLog()));
	}
}
