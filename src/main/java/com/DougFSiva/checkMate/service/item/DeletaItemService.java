package com.DougFSiva.checkMate.service.item;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.service.imagem.DeletaImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaItemService {

    private static final LoggerPadrao logger = new LoggerPadrao(DeletaItemService.class);
    private final ItemRepository repository;
    private final DeletaImagemService imagemService;
    
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
	public void deletar(Long ID) {
    	Item item = repository.findByIdOrElseThrow(ID);
    	repository.delete(item);
    	deletarImagem(item);
    	logger.info(String.format("Deletado item %s", item.infoParaLog()));
    }
    
    private void deletarImagem(Item item) {
		if (!item.getImagem().equals(ImagemConfig.getNomeImagemItemDefault())) {
			imagemService.deletarImagem(item.getImagem());
		}
	}
}
