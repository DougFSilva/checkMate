package com.DougFSiva.checkMate.service.usuario;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComUsuarioException;
import com.DougFSiva.checkMate.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidaUsuarioService {

	private final UsuarioRepository repository;
	
	public void validarUnicoEmail(String email) {
		if (repository.existsByEmail(email)) {
			throw new ErroDeOperacaoComUsuarioException(
					String.format("Usuário com email %s já existente", email));
		}
	}
	
	public void validarUnicoCPF(String CPF) {
		if (repository.existsByCPF(CPF)) {
			throw new ErroDeOperacaoComUsuarioException(
					String.format("Usuário com CPF %s já existente", CPF));
		}
	}
}
