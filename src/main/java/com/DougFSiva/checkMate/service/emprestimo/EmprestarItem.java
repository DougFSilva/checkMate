package com.DougFSiva.checkMate.service.emprestimo;

import org.springframework.stereotype.Service;

import com.DougFSiva.checkMate.repository.EmprestimoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmprestarItem {

	private EmprestimoRepository repository;
}
