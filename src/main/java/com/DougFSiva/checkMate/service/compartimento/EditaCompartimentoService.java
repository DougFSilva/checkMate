package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.CompartimentoForm;
import com.DougFSiva.checkMate.dto.response.CompartimentoResumoResponse;
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
	@PreAuthorize("hasRole('ADMIN')")
	@CacheEvict(value = "compartimentos", allEntries = true)
	public CompartimentoResumoResponse editar(Long ID, CompartimentoForm form) {
		Compartimento compartimento = repository.findByIdOrElseThrow(ID);
		if (!form.codigo().equals(compartimento.getCodigo())) {
			validaCompartimento.validarUnicoCodigo(form.codigo());
		}
		if (form.ambienteID() != compartimento.getAmbiente().getID()) {
			Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(form.ambienteID());
			compartimento.setAmbiente(ambiente);
		}
		compartimento.setCodigo(form.codigo());
		compartimento.setNome(form.nome());
		compartimento.setDescricao(form.descricao());
		Compartimento compartimentoSalvo = repository.save(compartimento);
		logger.info(String.format("Comapartimento %s editado", compartimento.infoParaLog()));
		return new CompartimentoResumoResponse(compartimentoSalvo);
	}
}
