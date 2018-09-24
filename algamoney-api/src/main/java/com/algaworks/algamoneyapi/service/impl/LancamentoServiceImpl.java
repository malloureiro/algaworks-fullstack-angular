package com.algaworks.algamoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.exceptionhandler.PessoaInativaException;
import com.algaworks.algamoneyapi.model.Lancamento;
import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.service.LancamentoService;
import com.algaworks.algamoneyapi.service.PessoaService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	@Autowired
	private PessoaService pessoaService;
	
	@Override
	public boolean verificarPessoaAtiva(Lancamento lancamento) {
		boolean pessoaAtiva = true;
		
		Pessoa pessoa = lancamento.getPessoa();
		
		Pessoa pessoaExistente = pessoaService.buscarPessoaPorCodigo(pessoa.getCodigo());
		
		if (pessoaExistente != null && pessoaExistente.isInativo()) {
			throw new PessoaInativaException();
		}
		return pessoaAtiva;
	}

}
