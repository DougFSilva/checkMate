package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.config.imagem.ImagemConfig;
import com.DougFSiva.checkMate.dto.form.CompartimentoForm;
import com.DougFSiva.checkMate.dto.response.CompartimentoResumoResponse;
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
	private final ValidaCompartimentoService validaCompartimento;
	
	@Transactional
	@PreAuthorize("hasRole('ADMIN')")
	@Caching(evict = {
	    	    @CacheEvict(value = "compartimentos_resumo_por_ambiente", allEntries = true ),
	    	    @CacheEvict(value = "compartimentos_resumo_todos", allEntries = true),
	    	    @CacheEvict(value = "ambientes_detalhado", allEntries = true )
	})
	public CompartimentoResumoResponse criar(CompartimentoForm form) {
		validaCompartimento.validarUnicoCodigo(form.codigo());
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(form.ambienteID());
		Compartimento compartimento = new Compartimento(ambiente,form.nome(), form.codigo(), form.descricao());
		String imagem = ImagemConfig.getNomeImagemCompartimentoDefault();
		compartimento.setImagem(imagem);
		Compartimento compartimentoSalvo = repository.save(compartimento);
		logger.info(String.format("Compartimento %s criado", compartimento.infoParaLog()));
		return new CompartimentoResumoResponse(compartimentoSalvo);
	}
	
}
