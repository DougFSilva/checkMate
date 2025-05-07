package com.DougFSiva.checkMate.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;
import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;
import com.DougFSiva.checkMate.model.usuario.Usuario;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

	default Ocorrencia findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Ocorrência com ID %d não encontrado!", ID)));
	}
	
    Page<Ocorrencia> findByDataHoraBetween(LocalDateTime dataInicial, LocalDateTime dataFinal, Pageable paginacao);

    Page<Ocorrencia> findByItemCheckList_CheckListCompartimento_CheckListAmbiente_Ambiente(Ambiente ambiente, Pageable paginacao);
    
    Page<Ocorrencia> findByItemCheckList_CheckListCompartimento(CheckListCompartimento checkList, Pageable paginacao);
    
    Page<Ocorrencia> findByResponsavelEncerramento(Usuario responsavelEncerramento, Pageable paginacao);
    
    Page<Ocorrencia> findAll(Pageable paginacao);
}
