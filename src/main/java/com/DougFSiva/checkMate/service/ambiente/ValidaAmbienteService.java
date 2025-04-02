package com.DougFSiva.checkMate.service.ambiente;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComAmbienteException;
import com.DougFSiva.checkMate.repository.AmbienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidaAmbienteService {

	private final AmbienteRepository repository;
	
	public void validarUnicoNome(String nome) {
		if (repository.existsByNome(nome)) {
			throw new ErroDeOperacaoComAmbienteException(
					String.format("Ambiente com nome %s já existente", nome));
		}
	}
}
