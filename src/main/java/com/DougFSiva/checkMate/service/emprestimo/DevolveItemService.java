package com.DougFSiva.checkMate.service.emprestimo;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.dto.response.EmprestimoResponse;
import com.DougFSiva.checkMate.exception.ErroDeOperacaoComEmprestimoException;
import com.DougFSiva.checkMate.model.Emprestimo;
import com.DougFSiva.checkMate.model.usuario.Usuario;
import com.DougFSiva.checkMate.repository.EmprestimoRepository;
import com.DougFSiva.checkMate.service.usuario.BuscaUsuarioAutenticado;
import com.DougFSiva.checkMate.util.LoggerPadrao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DevolveItemService {

	private static final LoggerPadrao logger = new LoggerPadrao(DevolveItemService.class);
	
	private final EmprestimoRepository repository;
	private final BuscaUsuarioAutenticado buscaUsuarioAutenticado;
	
	public EmprestimoResponse devolver(Long ID) {
		Emprestimo emprestimo = repository.findByIdOrElseThrow(ID);
		validarEmprestimoConcluido(emprestimo);
		Usuario recebedor = buscaUsuarioAutenticado.buscar();
		emprestimo.setDataHoraDevolucao(LocalDateTime.now());
		emprestimo.setDevolvido(true);
		emprestimo.setRecebedor(recebedor);
		Emprestimo emprestimoSalvo = repository.save(emprestimo);
		logger.infoComUsuario(String.format(
				"Empréstimo %d concluído por %s", emprestimo.getID(), recebedor.infoParaLog()));
		return new EmprestimoResponse(emprestimoSalvo);
	}
	
	private void validarEmprestimoConcluido(Emprestimo emprestimo) {
		if (emprestimo.isDevolvido()) {
			throw new ErroDeOperacaoComEmprestimoException(
					"Não é possível devolver o item pois ele já foi devolvido!");
		}
	}
}
