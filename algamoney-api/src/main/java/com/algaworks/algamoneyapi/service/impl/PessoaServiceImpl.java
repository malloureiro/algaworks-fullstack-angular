package com.algaworks.algamoneyapi.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.repository.PessoaRepository;
import com.algaworks.algamoneyapi.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Override
	public Pessoa salvar(Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		return pessoaSalva;
	}
	
	@Override
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaExistente = buscarPessoaPorCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaExistente, "codigo");
		return pessoaRepository.save(pessoaExistente);
	}

	@Override
	public Pessoa atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaExistente = buscarPessoaPorCodigo(codigo);
		pessoaExistente.setAtivo(ativo);
		return pessoaRepository.save(pessoaExistente);
	}
	
	@Override
	public Pessoa buscarPessoaPorCodigo(Long codigo) {
		Pessoa pessoaExistente = pessoaRepository.findOne(codigo);
		if (pessoaExistente == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaExistente;
	}

	@Override
	public void remover(Long codigo) {
		pessoaRepository.delete(codigo);
	}

	@Override
	public List<Pessoa> buscarTodos() {
		return pessoaRepository.findAll();
	}
	
	
}
