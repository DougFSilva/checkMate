package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
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

	private static final LoggerPadrao logger = new LoggerPadrao(DeletaCompartimentoService.class);
	private final CompartimentoRepository repository;
	private final ItemRepository itemRepository;
	private final DeletaImagemService imagemService;
	
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@Caching(evict = {
    	    @CacheEvict(value = "compartimentos_resumo_por_ambiente", allEntries = true ),
    	    @CacheEvict(value = "compartimentos_resumo_todos", allEntries = true),
    	    @CacheEvict(value = "compartimentos_detalhado", allEntries = true),
    	    @CacheEvict(value = "ambientes_detalhado", allEntries = true )
	})
	public void deletar(Long ID) {
		Compartimento compartimento = repository.findByIdOrElseThrow(ID);
		if (itemRepository.existsByCompartimento(compartimento)) {
			throw new ErroDeOperacaoComCompartimentoException(
					"Não é possível deletar compartimento, pois há itens associados a ele");
		}
		repository.delete(compartimento);
		deletarImagem(compartimento);
		logger.info(String.format("Compartimento %s deletado", compartimento.infoParaLog()));
	}
	
	private void deletarImagem(Compartimento compartimento) {
		if (!compartimento.getImagem().equals(ImagemConfig.getNomeImagemCompartimentoDefault())) {
			imagemService.deletarImagem(compartimento.getImagem());
		}
	}
	
}
