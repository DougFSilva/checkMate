package com.DougFSiva.checkMate.service.item;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComItemException;
import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.repository.ItemCheckListRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.service.imagem.DeletaImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaItemService {

    private static final LoggerPadrao logger = new LoggerPadrao(DeletaItemService.class);
    private final ItemRepository repository;
    private final ItemCheckListRepository itemCheckListRepository;
    private final DeletaImagemService imagemService;
    
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "itens", allEntries = true)
	public void deletar(Long ID) {
    	Item item = repository.findByIdOrElseThrow(ID);
    	if (itemCheckListRepository.existsByItem(item)) {
    		throw new ErroDeOperacaoComItemException(
    				String.format("Não é possível deletar item com ID %s, pois há checklists associados a ele", item.getID() ));
    	}
    	repository.delete(item);
    	deletarImagem(item);
    	logger.info(String.format("Item %s deletado", item.infoParaLog()));
    }
    
    private void deletarImagem(Item item) {
		if (!item.getImagem().equals(ImagemConfig.getNomeImagemItemDefault())) {
			imagemService.deletarImagem(item.getImagem());
		}
	}
    

}
