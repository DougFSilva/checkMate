package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComCompartimentoException;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.service.imagem.DeletaImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaCompartimentoService {

	private static final LoggerPadrao logger = new LoggerPadrao(EditaCompartimentoService.class);
	private final CompartimentoRepository repository;
	private final ItemRepository itemRepository;
	private final DeletaImagemService imagemService;
	
	@Transactional
	public void deletar(Long ID) {
		Compartimento compartimento = repository.findByIdOrElseThrow(ID);
		if (itemRepository.existsByCompartimento(compartimento)) {
			throw new ErroDeOperacaoComCompartimentoException(
					"Não é possível deletar compartimento, pois há itens associados a ele");
		}
		repository.delete(compartimento);
		imagemService.deletarImagemDeCompartimento(compartimento);
		logger.infoComUsuario(String.format("Deletado compartimento %s", compartimento.infoParaLog()));
	}
	
}
