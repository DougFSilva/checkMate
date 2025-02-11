package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.form.EditaCompartimentoForm;
import com.DougFSiva.checkMate.dto.response.CompartimentoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.service.ImagemService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

@Service
public class EditaCompartimentoService {

	private static final LoggerPadrao logger = new LoggerPadrao(EditaCompartimentoService.class);
	private final CompartimentoRepository repository;
	private final AmbienteRepository ambienteRepository;
	private final ImagemService imagemService;
	
	public EditaCompartimentoService(CompartimentoRepository repository, AmbienteRepository ambienteRepository,
			ImagemService imagemService) {
		this.repository = repository;
		this.ambienteRepository = ambienteRepository;
		this.imagemService = imagemService;
	}

	public CompartimentoResponse editar(EditaCompartimentoForm form) {
		Compartimento compartimento = repository.findByIdOrElseThrow(form.ID());
		if (form.ambienteID() != compartimento.getAmbiente().getID()) {
			Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(form.ambienteID());
			compartimento.setAmbiente(ambiente);
		}
		compartimento.setCodigo(form.codigo());
		compartimento.setNome(form.nome());
		compartimento.setDescricao(form.descricao());
		String imagem = form.imagem() != null 
				? imagemService.salvarImagemDeCompartimento(form.imagem(), compartimento)
				: imagemService.buscarImagemDefaultDeCompartimento();
		compartimento.setImagem(imagem);
		Compartimento compartimentoSalvo = repository.save(compartimento);
		logger.infoComUsuario(String.format("Editado comaprtimento %s", compartimento.infoParaLog()));
		return new CompartimentoResponse(compartimentoSalvo);
	}
}
