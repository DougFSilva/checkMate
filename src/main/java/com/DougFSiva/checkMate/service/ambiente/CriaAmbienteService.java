package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.form.CriaAmbienteForm;
import com.DougFSiva.checkMate.dto.response.AmbienteResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.service.ImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

@Service
public class CriaAmbienteService {

    private static final LoggerPadrao logger = new LoggerPadrao(CriaAmbienteService.class);
	private final AmbienteRepository repository;
	private final ImagemService imagemService;
	
	public CriaAmbienteService(AmbienteRepository repository, ImagemService imagemService) {
		this.repository = repository;
		this.imagemService = imagemService;
	}

	public AmbienteResponse criar(CriaAmbienteForm form) {
		Ambiente ambiente = new Ambiente(form.nome(), form.descricao(), form.localizacao());
		String imagem = form.imagem() != null 
				? imagemService.salvarImagemDeAmbiente(form.imagem(), ambiente)
				: imagemService.buscarImagemDefaultDeAmbiente();
		ambiente.setImagem(imagem);
		Ambiente ambienteSalvo = repository.save(ambiente);
		logger.infoComUsuario(String.format("Ambiente %s criado", ambienteSalvo));
		return new AmbienteResponse(ambienteSalvo);
	}
}
