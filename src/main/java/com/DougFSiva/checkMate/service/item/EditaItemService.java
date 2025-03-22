package com.DougFSiva.checkMate.service.item;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.form.EditaItemForm;
import com.DougFSiva.checkMate.dto.response.ItemResponse;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditaItemService {

    private static final LoggerPadrao logger = new LoggerPadrao(EditaItemService.class);
    private final ItemRepository repository;
    private final CompartimentoRepository compartimentoRepository;
    
    public ItemResponse editar(EditaItemForm form) {
    	Item item = repository.findByIdOrElseThrow(form.ID());
    	if (form.compartimentoID() != item.getCompartimento().getID()) {
    		Compartimento compartimento = compartimentoRepository.findByIdOrElseThrow(form.compartimentoID());
    		item.setCompartimento(compartimento);
    	}
    	item.setDescricao(form.descricao());
    	item.setQuantidade(form.quantidade());
    	Item itemSalvo = repository.save(item);
    	logger.infoComUsuario(String.format("Item %s editado", item.infoParaLog()));
    	return new ItemResponse(itemSalvo);
    }

}
