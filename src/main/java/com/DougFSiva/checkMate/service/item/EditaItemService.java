package com.DougFSiva.checkMate.service.item;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.ItemForm;
import com.DougFSiva.checkMate.dto.response.ItemDetalhadoResponse;
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
    
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @CacheEvict(value = "itens", allEntries = true)
    public ItemDetalhadoResponse editar(Long ID, ItemForm form) {
    	Item item = repository.findByIdOrElseThrow(ID);
    	if (form.compartimentoID() != item.getCompartimento().getID()) {
    		Compartimento compartimento = compartimentoRepository.findByIdOrElseThrow(form.compartimentoID());
    		item.setCompartimento(compartimento);
    	}
    	item.setDescricao(form.descricao());
    	item.setQuantidade(form.quantidade());
    	item.setVerificavel(form.verificavel());
    	Item itemSalvo = repository.save(item);
    	logger.info(String.format("Item %s editado", item.infoParaLog()));
    	return new ItemDetalhadoResponse(itemSalvo);
    }

}
