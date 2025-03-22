package com.DougFSiva.checkMate.service.item;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaItemService {

    private static final LoggerPadrao logger = new LoggerPadrao(DeletaItemService.class);
    private final ItemRepository repository;
    
	public void deletar(Long ID) {
    	Item item = repository.findByIdOrElseThrow(ID);
    	repository.delete(item);
    	logger.infoComUsuario(String.format("Deletado item %s", item.infoParaLog()));
    }
}
