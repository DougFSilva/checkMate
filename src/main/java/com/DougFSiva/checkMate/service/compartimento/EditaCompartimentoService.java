package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.CompartimentoForm;
import com.DougFSiva.checkMate.dto.response.CompartimentoResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.Compartimento;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditaCompartimentoService {

	private static final LoggerPadrao logger = new LoggerPadrao(EditaCompartimentoService.class);
	private final CompartimentoRepository repository;
	private final AmbienteRepository ambienteRepository;
	private final ValidaCompartimentoService validaCompartimento;
	
	@Transactional
	public CompartimentoResponse editar(Long ID, CompartimentoForm form) {
		validaCompartimento.validarUnicoCodigo(form.codigo());
		Compartimento compartimento = repository.findByIdOrElseThrow(ID);
		if (form.ambienteID() != compartimento.getAmbiente().getID()) {
			Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(form.ambienteID());
			compartimento.setAmbiente(ambiente);
		}
		compartimento.setCodigo(form.codigo());
		compartimento.setNome(form.nome());
		compartimento.setDescricao(form.descricao());
		Compartimento compartimentoSalvo = repository.save(compartimento);
		logger.infoComUsuario(String.format("Editado comaprtimento %s", compartimento.infoParaLog()));
		return new CompartimentoResponse(compartimentoSalvo);
	}
}
