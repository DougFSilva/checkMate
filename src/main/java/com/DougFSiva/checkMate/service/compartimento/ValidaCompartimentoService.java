package com.DougFSiva.checkMate.service.compartimento;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComCompartimentoException;
import com.DougFSiva.checkMate.repository.CompartimentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidaCompartimentoService {

	private final CompartimentoRepository repository;
	
	public void validarUnicoCodigo(String codigo) {
		if (repository.existsByCodigo(codigo)) {
			throw new ErroDeOperacaoComCompartimentoException(
					String.format("Compartimento com código %s já existente", codigo));
		}
	}
}
