package com.DougFSiva.checkMate.service.emprestimo;

import java.time.LocalDateTime;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.form.EmprestaItemForm;
import com.DougFSiva.checkMate.dto.response.EmprestimoResumoResponse;
import com.DougFSiva.checkMate.model.Emprestimo;
import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.EmprestimoRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;
import com.DougFSiva.checkMate.repository.UsuarioRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmprestaItemService {

	private static final LoggerPadrao logger = new LoggerPadrao(EmprestaItemService.class);
	
	private final EmprestimoRepository repository;
	private final ItemRepository itemRepository;
	private final UsuarioRepository usuarioRepository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	
	@Transactional
	@PreAuthorize("hasAnyRole('ADMIN','PROFESSOR', 'FUNCIONARIO')")
	@CacheEvict(value = "emprestimos", allEntries = true)
	public EmprestimoResumoResponse emprestar(EmprestaItemForm form) {
		Item item = itemRepository.findByIdOrElseThrow(form.itemID());
		Usuario solicitante = usuarioRepository.findByIdOrElseThrow(form.solicitanteID());
		Usuario emprestador = buscaUsuarioAutenticado.buscar();
		Emprestimo emprestimo = new Emprestimo(item, emprestador, solicitante, LocalDateTime.now());
		Emprestimo EmprestimoSalvo = repository.save(emprestimo);
		logger.info(String.format("Item %s emprestado por %s para %s", 
				item.infoParaLog(), emprestador.infoParaLog(), solicitante.infoParaLog()));
		return new EmprestimoResumoResponse(EmprestimoSalvo);
	}
	
}
