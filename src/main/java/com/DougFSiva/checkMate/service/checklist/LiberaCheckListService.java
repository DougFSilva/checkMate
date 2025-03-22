package com.DougFSiva.checkMate.service.checklist;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.exception.ErroDeOperacaoComCheckListException;
import com.DougFSiva.checkMate.model.checklist.CheckList;
import com.DougFSiva.checkMate.model.checklist.CheckListStatus;
import com.DougFSiva.checkMate.repository.CheckListRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LiberaCheckListService {

	private final CheckListRepository repository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	
	public void liberarCheckList(Long ID) {
		CheckList checkList = repository.findByIdOrElseThrow(ID);
		validarCheckListEntradaPreenchido(checkList);
		checkList.setDataHoraLiberacao(LocalDateTime.now());
		checkList.setResponsavelLiberacao(buscaUsuarioAutenticado.buscar());
		checkList.setStatus(CheckListStatus.LIBERADO);
		repository.save(checkList);
		
	}
	
	private void validarCheckListEntradaPreenchido(CheckList checkList) {
		if (checkList.getStatus() != CheckListStatus.ENTRADA_PREENCHIDO) {
			throw new ErroDeOperacaoComCheckListException(
					"O check-list só pode ser liberado se tiver status de preenchimento de entrada");
		}
	}
}
