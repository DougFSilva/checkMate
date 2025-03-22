package com.DougFSiva.checkMate.service.ocorrencia;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComOcorrenciaException;
import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncerraOcorrenciaService {

	private final OcorrenciaRepository repository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	
	public void encerrar(Long id) {
		Ocorrencia ocorrencia = repository.findByIdOrElseThrow(id);
		validarOcorrenciaTratada(ocorrencia);
		ocorrencia.setEncerrada(true);
		ocorrencia.setResponsavelEncerramento(buscaUsuarioAutenticado.buscar());
		repository.save(ocorrencia);
	}
	
	private void validarOcorrenciaTratada(Ocorrencia ocorrencia) {
		if (ocorrencia.getTratamentos().size() < 1) {
			throw new ErroDeOperacaoComOcorrenciaException("Só é possível encerrar uma ocorrência que foi tratada");
		}
	}
}
