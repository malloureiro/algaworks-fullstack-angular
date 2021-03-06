package com.algaworks.algamoneyapi.service;

import java.util.List;

import com.algaworks.algamoneyapi.model.Pessoa;

public interface PessoaService {
	
	public Pessoa salvar(Pessoa pessoa);
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa);

	public Pessoa atualizarPropriedadeAtivo(Long codigo, Boolean ativo);
	
	public Pessoa buscarPessoaPorCodigo(Long codigo);
	
	public void remover(Long codigo);
	
	public List<Pessoa> buscarTodos();
	
}
