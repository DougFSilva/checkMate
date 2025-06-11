package com.DougFSiva.checkMate.service.emprestimo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DougFSiva.checkMate.dto.response.EmprestimoDetalhadoResponse;
import com.DougFSiva.checkMate.dto.response.EmprestimoResumoResponse;
import com.DougFSiva.checkMate.dto.response.EmprestimoResumoSemItemResponse;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.Item;
import com.DougFSiva.checkMate.repository.AmbienteRepository;
import com.DougFSiva.checkMate.repository.EmprestimoRepository;
import com.DougFSiva.checkMate.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaEmprestimoService {

	private final EmprestimoRepository repository;
	private final AmbienteRepository ambienteRepository;
	private final ItemRepository itemRepository;
	
	@Cacheable(value = "emprestimos", key = "'emprestimoID_' + #ID")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public EmprestimoDetalhadoResponse buscarPeloID(Long ID) {
		return new EmprestimoDetalhadoResponse(repository.findByIdOrElseThrow(ID));
	}
	
	@Cacheable(value = "emprestimos", key = "'emprestimosPeloItem_' + #itemID + '_pagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize + '_sort_' + #paginacao.getSort().toString()")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<EmprestimoResumoSemItemResponse> buscarPeloItem(Long itemID, Pageable paginacao) {
		Item item = itemRepository.findByIdOrElseThrow(itemID);
		return repository.findByItem(item, paginacao).map(EmprestimoResumoSemItemResponse::new);
	}

	@Cacheable(value = "emprestimos", key = "'emprestimosPeloAmbiente_' + #ambienteID + '_pagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize + '_sort_' + #paginacao.getSort().toString()")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<EmprestimoResumoResponse> buscarPeloAmbiente(Long ambienteID, Pageable paginacao) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByItem_Compartimento_Ambiente(ambiente, paginacao).map(EmprestimoResumoResponse::new);
	}

	@Cacheable(value = "emprestimos", key = "'emprestimosPeloStatus_' + #devolvido + '_pagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize + '_sort_' + #paginacao.getSort().toString()")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<EmprestimoResumoResponse> buscarPeloStatusDevolvido(boolean devolvido, Pageable paginacao) {
		return repository.findByDevolvido(devolvido, paginacao).map(EmprestimoResumoResponse::new);
	}

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<EmprestimoResumoResponse> buscarPelaDataDeEmprestimo(LocalDate dataInicial, LocalDate dataFinal,
			Pageable paginacao) {
		return repository.findByDataHoraEmprestimoBetween(
				dataInicial.atStartOfDay(), dataFinal.atTime(LocalTime.MAX), paginacao).map(EmprestimoResumoResponse::new);
	}

	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<EmprestimoResumoResponse> buscarPelaDataDeDevolucao(LocalDate dataInicial, LocalDate dataFinal,
			Pageable paginacao) {
		return repository.findByDataHoraDevolucaoBetween(
				dataInicial.atStartOfDay(), dataFinal.atTime(LocalTime.MAX), paginacao).map(EmprestimoResumoResponse::new);
	}
	
	@Cacheable(value = "emprestimos", key = "'todosEmprestimosPagina_' + #paginacao.pageNumber + '_tamanho_' + #paginacao.pageSize + '_sort_' + #paginacao.getSort().toString()")
	@PreAuthorize("isAuthenticated()")
	@Transactional(readOnly = true)
	public Page<EmprestimoResumoResponse> buscarTodos(Pageable pagimacao) {
		return repository.findAll(pagimacao).map(EmprestimoResumoResponse::new);
	}

}
