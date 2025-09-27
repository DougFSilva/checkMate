package com.DougFSiva.checkMate.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.DougFSiva.checkMate.exception.ObjetoNaoEncontradoException;
import com.DougFSiva.checkMate.model.Ambiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbiente;
import com.DougFSiva.checkMate.model.checklist.CheckListAmbienteStatus;
import com.DougFSiva.checkMate.model.usuario.Usuario;

public interface CheckListAmbienteRepository extends JpaRepository<CheckListAmbiente, Long> {

	default CheckListAmbiente findByIdOrElseThrow(Long ID) {
		return findById(ID).orElseThrow(() -> new ObjetoNaoEncontradoException(
				String.format("Check-list de ambiente com ID %d não encontrado!", ID)));
	}

	Page<CheckListAmbiente> findByAmbiente(Ambiente ambiente, Pageable paginacao);

	Page<CheckListAmbiente> findByAmbienteAndStatus(Ambiente ambiente, CheckListAmbienteStatus status,
			Pageable paginacao);

	Page<CheckListAmbiente> findByAmbienteAndDataHoraEncerramentoBetween(Ambiente ambiente, LocalDateTime dataInicial,
			LocalDateTime dataFinal, Pageable paginacao);

	Page<CheckListAmbiente> findByDataHoraAberturaBetween(LocalDateTime dataInicial, LocalDateTime dataFinal,
			Pageable paginacao);

	Page<CheckListAmbiente> findByDataHoraEncerramentoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal,
			Pageable paginacao);

	List<CheckListAmbiente> findByDataHoraEncerramentoIsNotNullAndDataHoraEncerramentoBefore(LocalDateTime dataHora);

	Page<CheckListAmbiente> findByStatus(CheckListAmbienteStatus status, Pageable paginacao);
	
	List<CheckListAmbiente> findByStatus(CheckListAmbienteStatus status);
	
	long countByStatus(CheckListAmbienteStatus status);

	boolean existsByAmbiente(Ambiente ambiente);

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM CheckListAmbiente c WHERE c.responsavelAbertura = :usuario OR c.responsavelLiberacao = :usuario OR c.responsavelEncerramento = :usuario")
	boolean existsByResponsavel(@Param("usuario") Usuario usuario);

}
