package com.DougFSiva.checkMate.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListCompartimento;
import com.DougFSiva.checkMate.model.checklist.ItemCheckList;
import com.DougFSiva.checkMate.model.ocorrrencia.Ocorrencia;
import com.DougFSiva.checkMate.model.usuario.Usuario;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

	default Ocorrencia findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Ocorrência com ID %d não encontrado!", ID)));
	}
	
	void deleteByItemCheckListIn(List<ItemCheckList> item);
	
    Page<Ocorrencia> findByDataHoraBetween(OffsetDateTime dataInicial, OffsetDateTime dataFinal, Pageable paginacao);

    Page<Ocorrencia> findByItemCheckList_CheckListCompartimento_CheckListAmbiente_Ambiente(Ambiente ambiente, Pageable paginacao);
    
    Page<Ocorrencia> findByItemCheckList_CheckListCompartimento_CheckListAmbiente_AmbienteAndDataHoraBetween(
    		Ambiente ambiente, OffsetDateTime dataInicial, OffsetDateTime dataFinal, Pageable paginacao);
    
    Page<Ocorrencia> findByItemCheckList_CheckListCompartimento(CheckListCompartimento checkList, Pageable paginacao);
    
    Page<Ocorrencia> findByResponsavelEncerramento(Usuario responsavelEncerramento, Pageable paginacao);
    
    Page<Ocorrencia> findByEncerrada(boolean encerrada, Pageable paginacao);
    
    List<Ocorrencia> findByItemCheckList_CheckListCompartimento_CheckListAmbiente(CheckListAmbiente checkList);
    
    Page<Ocorrencia> findAll(Pageable paginacao);
    
    boolean existsByItemCheckList(ItemCheckList item);
    
    boolean existsByResponsavelEncerramento(Usuario usuario);
}
