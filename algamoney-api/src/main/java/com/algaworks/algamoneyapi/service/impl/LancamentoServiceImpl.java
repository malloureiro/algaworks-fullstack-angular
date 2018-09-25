package com.algaworks.algamoneyapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.exceptionhandler.PessoaInativaOuInexistenteException;
import com.algaworks.algamoneyapi.model.Lancamento;
import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.repository.LancamentoRepository;
import com.algaworks.algamoneyapi.service.LancamentoService;
import com.algaworks.algamoneyapi.service.PessoaService;

@Service
public class LancamentoServiceImpl implements LancamentoService {


	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaService pessoaService;

	@Override
	public Lancamento salvarLancamento(Lancamento lancamento) {
		boolean inativo = this.verificarPessoaAtiva(lancamento);
		
		Lancamento lancamentoSalvo = null;
		
		if (!inativo) {
			 lancamentoSalvo = lancamentoRepository.save(lancamento);
		}
		
		return lancamentoSalvo;
	}
	
	public boolean verificarPessoaAtiva(Lancamento lancamento) {
		boolean pessoaAtiva = true;
		
		Pessoa pessoa = lancamento.getPessoa();
		
		Pessoa pessoaExistente = null;
		
		try {
			pessoaExistente = pessoaService.buscarPessoaPorCodigo(pessoa.getCodigo());
			
			boolean inativo = pessoaExistente.isInativo();
			
			if (pessoaExistente != null && inativo) {
				throw new PessoaInativaOuInexistenteException();
			}
			
		} catch (EmptyResultDataAccessException ex) {
			throw new PessoaInativaOuInexistenteException();
		}
		
		return pessoaAtiva;
	}

}
