package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.dto.form.CriaCompartimentoForm;
import com.DougFSiva.checkMate.dto.response.CompartimentoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.service.ambiente.CriaAmbienteService;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriaCompartimentoService {

	private static final LoggerPadrao logger = new LoggerPadrao(CriaAmbienteService.class);
	private final CompartimentoRepository repository;
	private final AmbienteRepository ambienteRepository;
	
	@Transactional
	public CompartimentoResponse criar(CriaCompartimentoForm form) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(form.ambienteID());
		Compartimento compartimento = new Compartimento(ambiente,form.nome(), form.codigo(), form.descricao());
		String imagem = ImagemConfig.getNomeImagemCompartimentoDefault();
		compartimento.setImagem(imagem);
		Compartimento compartimentoSalvo = repository.save(compartimento);
		logger.infoComUsuario(String.format("Criado compartimento %s", compartimento.infoParaLog()));
		return new CompartimentoResponse(compartimentoSalvo);
	}
	
}
