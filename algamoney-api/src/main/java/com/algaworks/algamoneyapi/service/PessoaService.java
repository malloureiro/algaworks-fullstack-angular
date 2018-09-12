package com.algaworks.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaExistente = buscarPessoaPorCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaExistente, "codigo");
		return pessoaRepository.save(pessoaExistente);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaExistente = buscarPessoaPorCodigo(codigo);
		pessoaExistente.setAtivo(ativo);
		pessoaRepository.save(pessoaExistente);
	}
	
	private Pessoa buscarPessoaPorCodigo(Long codigo) {
		Pessoa pessoaExistente = pessoaRepository.findOne(codigo);
		if (pessoaExistente == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaExistente;
	}
}
