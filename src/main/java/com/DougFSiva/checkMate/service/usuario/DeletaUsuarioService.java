package com.DougFSiva.checkMate.service.usuario;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComUsuarioException;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.CheckListAmbienteRepository;
import com.DougFSiva.checkMate.repository.EmprestimoRepository;
import com.DougFSiva.checkMate.repository.OcorrenciaRepository;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletaUsuarioService {

	private final static LoggerPadrao logger = new LoggerPadrao(DeletaUsuarioService.class);
	private final UsuarioRepository repository;
	private final EmprestimoRepository emprestimoRepository;
	private final OcorrenciaRepository ocorrenciaRepository;
	private final CheckListAmbienteRepository checkListAmbienteRepository;

	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN', 'SISTEMA')")
	@CacheEvict(value = "usuarios", allEntries = true)
	public void deletar(Long ID) {
		Usuario usuario = repository.findByIdOrElseThrow(ID);
		validaUsuarioSemEmprestimo(usuario);
		validaUsuarioSemOcorrencia(usuario);
		validaUsuarioSemCheckListAmbiente(usuario);
		repository.delete(usuario);
		logger.info(String.format("Usuário %s deletado", usuario.infoParaLog()));
	}
	
	private void validaUsuarioSemEmprestimo(Usuario usuario) {
		if (emprestimoRepository.existsByUsuario(usuario)) {
			throw new ErroDeOperacaoComUsuarioException("Não é possível deletar usuário, pois há empréstimos associados a ele");
		}
	}
	
	private void validaUsuarioSemOcorrencia(Usuario usuario) {
		if (ocorrenciaRepository.existsByResponsavelEncerramento(usuario)) {
			throw new ErroDeOperacaoComUsuarioException("Não é possível deletar usuário, pois há ocorrências associadas a ele");
		}
	}
	
	private void validaUsuarioSemCheckListAmbiente(Usuario usuario) {
		if (checkListAmbienteRepository.existsByResponsavel(usuario)) {
			throw new ErroDeOperacaoComUsuarioException("Não é possível deletar usuário, pois há checklists associados a ele");
		}
	}

}
