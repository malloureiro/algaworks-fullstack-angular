package com.algaworks.algamoneyapi.service;

import com.algaworks.algamoneyapi.model.Pessoa;

public interface PessoaService {
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa);

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo);
	
}
