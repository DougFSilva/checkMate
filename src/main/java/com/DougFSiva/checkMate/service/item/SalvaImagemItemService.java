package com.DougFSiva.checkMate.service.item;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.dto.response.ItemResponse;
import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.service.imagem.SalvaImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalvaImagemItemService {

    private static final LoggerPadrao logger = new LoggerPadrao(SalvaImagemItemService.class);

	private final ItemRepository repository;
	private final SalvaImagemService salvaImagemService;
	
	@Transactional
	public ItemResponse salvar(MultipartFile imagem, Long ID) {
		Item item = repository.findByIdOrElseThrow(ID);
		String nomeImagem = String.format("%s/%d-%s", 
				ImagemConfig.PASTA_IMAGEM_ITEM, 
				item.getID(), 
				item.getDescricao());
		String nomeImagemSalva = salvaImagemService.salvarImagem(imagem, nomeImagem);
		item.setImagem(nomeImagemSalva);
		Item itemSalvo = repository.save(item);
		logger.infoComUsuario(String.format("Alterada imagem de Item %s", item.infoParaLog()));
		return new ItemResponse(itemSalvo);
	}
	
}
