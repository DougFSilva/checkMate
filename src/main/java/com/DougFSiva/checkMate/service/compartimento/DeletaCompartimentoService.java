package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.service.ImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

@Service
public class DeletaCompartimentoService {

	private static final LoggerPadrao logger = new LoggerPadrao(EditaCompartimentoService.class);
	private final CompartimentoRepository repository;
	private final ImagemService imagemService;
	
	public DeletaCompartimentoService(CompartimentoRepository repository, ImagemService imagemService) {
		this.repository = repository;
		this.imagemService = imagemService;
	}

	public void deletar(Long ID) {
		Compartimento compartimento = repository.findByIdOrElseThrow(ID);
		repository.delete(compartimento);
		imagemService.deletarImagemDeCompartimento(compartimento);
		logger.infoComUsuario(String.format("Deletado compartimento %s", compartimento.infoParaLog()));
	}
	
}
