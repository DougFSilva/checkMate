package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComAmbienteException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.service.imagem.DeletaImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaAmbienteService {

	private static final LoggerPadrao logger = new LoggerPadrao(DeletaAmbienteService.class);
	private final AmbienteRepository repository;
	private final CheckListAmbienteRepository checkListRepository;
	private final CompartimentoRepository compartimentoRepository;
	private final DeletaImagemService imagemService;
	
	@Transactional
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
		deletarImagem(ambiente);
		logger.infoComUsuario(String.format("Deletado Ambiente %s", ambiente.infoParaLog()));
	}
	
	private void deletarImagem(Ambiente ambiente) {
		if (!ambiente.getImagem().equals(ImagemConfig.getNomeImagemAmbienteDefault())) {
			imagemService.deletarImagem(ambiente.getImagem());
		}
	}
}
