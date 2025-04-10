package com.DougFSiva.checkMate.service.emprestimo;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.EmprestimoResponse;
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
	
	public EmprestimoResponse buscarPeloID(Long ID) {
		return new EmprestimoResponse(repository.findByIdOrElseThrow(ID));
	}
	
	public Page<EmprestimoResponse> buscarPeloItem(Long itemID, Pageable paginacao) {
		Item item = itemRepository.findByIdOrElseThrow(itemID);
		return repository.findByItem(item, paginacao).map(EmprestimoResponse::new);
	}

	public Page<EmprestimoResponse> buscarPeloAmbiente(Long ambienteID, Pageable paginacao) {
		Ambiente ambiente = ambienteRepository.findByIdOrElseThrow(ambienteID);
		return repository.findByItem_Compartimento_Ambiente(ambiente, paginacao).map(EmprestimoResponse::new);
	}

	public Page<EmprestimoResponse> buscarPeloStatusDevolvido(boolean devolvido, Pageable paginacao) {
		return repository.findByDevolvido(devolvido, paginacao).map(EmprestimoResponse::new);
	}

	public Page<EmprestimoResponse> buscarPelaDataDeEmprestimo(LocalDate dataInicial, LocalDate dataFinal,
			Pageable paginacao) {
		return repository.findByDataHoraEmprestimoBetween(dataInicial, dataFinal, paginacao).map(EmprestimoResponse::new);
	}

	public Page<EmprestimoResponse> buscarPelaDataDeDevolucao(LocalDate dataInicial, LocalDate dataFinal,
			Pageable paginacao) {
		return repository.findByDataHoraDevolucaoBetween(dataInicial, dataFinal, paginacao).map(EmprestimoResponse::new);
	}
	
	public Page<EmprestimoResponse> buscarTodas(Pageable pagimacao) {
		return repository.findAll(pagimacao).map(EmprestimoResponse::new);
	}

}
