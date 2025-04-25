package com.DougFSiva.checkMate.service.item;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.dto.form.ItemForm;
import com.DougFSiva.checkMate.dto.response.ItemResponse;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriaItemService {

    private static final LoggerPadrao logger = new LoggerPadrao(CriaItemService.class);
    private final ItemRepository repository;
    private final CompartimentoRepository compartimentoRepository;
    
    @Transactional
	public ItemResponse criar(ItemForm form) {
    	Compartimento compartimento = compartimentoRepository.findByIdOrElseThrow(form.compartimentoID());
    	Item item = new Item(compartimento, form.descricao(), form.quantidade());
    	item.setImagem(ImagemConfig.getNomeImagemItemDefault());
    	Item itemSalvo = repository.save(item);
    	logger.infoComUsuario(String.format("Criado item %s", item.infoParaLog()));
    	return new ItemResponse(itemSalvo);
    }
}
