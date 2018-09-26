package com.algaworks.algamoneyapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algamoneyapi.model.Pessoa;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class PessoaServiceIntegrationTests {

	@Autowired
	private PessoaService pessoaService;
	
	private Pessoa aPessoa;
	
	private Pessoa pessoaSalva;
	
	@Before
	public void init() {
		aPessoa = new Pessoa();
		aPessoa.setNome("Teste Integração Pessoa");
		aPessoa.setAtivo(true);
		
		pessoaSalva = pessoaService.salvar(aPessoa);
		assertNotNull(pessoaSalva);
		assertNotNull(pessoaSalva.getCodigo());
		assertEquals(pessoaSalva.getNome(), aPessoa.getNome());
	}
	
	@Test
	public void testInserirNovaPessoa() {
		Pessoa pessoaSalva = pessoaService.salvar(aPessoa);
		assertNotNull(pessoaSalva);
		assertNotNull(pessoaSalva.getCodigo());
		assertEquals(pessoaSalva.getNome(), aPessoa.getNome());
	}
	
	@Test
	public void testAtualizarPessoa() {
		pessoaSalva.setNome("Teste Pessoa Atualizada");
		Pessoa pessoaAtualizada = pessoaService.atualizar(pessoaSalva.getCodigo(), pessoaSalva);
		
		assertEquals(pessoaSalva.getNome(), pessoaAtualizada.getNome());
	}
	
	//@Test
	public void testAtivarInativarPessoa() {
		
		boolean ativo = false;
		
		pessoaService.atualizarPropriedadeAtivo(pessoaSalva.getCodigo(), ativo);
		assertEquals(pessoaSalva.getAtivo(), ativo);
		
		ativo = true;
		
		pessoaService.atualizarPropriedadeAtivo(pessoaSalva.getCodigo(), ativo);
		assertEquals(pessoaSalva.getAtivo(), ativo);
		
		// considerar o retorno do objeto de pessoa atualizada apenas para garantir a assertividade do teste faz sentido?
	}
	
	@Test
	public void testBuscarPessoaPorCodigo() {
		Pessoa pessoa = pessoaService.buscarPessoaPorCodigo(pessoaSalva.getCodigo());
		assertNotNull(pessoa);
		assertEquals(pessoaSalva.getCodigo(), pessoa.getCodigo());
		assertEquals(pessoaSalva.getNome(), pessoa.getNome());
		
	}
	
	@Test(expected =  EmptyResultDataAccessException.class)
	public void testBuscarPessoaPorCodigoInexistente() {
		Pessoa pessoa = pessoaService.buscarPessoaPorCodigo(999999L);
		assertNull(pessoa);
	}
	
	@Test(expected =  EmptyResultDataAccessException.class)
	public void testRemoverPessoa() {
		pessoaService.remover(pessoaSalva.getCodigo());
		
		Pessoa pessoa = pessoaService.buscarPessoaPorCodigo(pessoaSalva.getCodigo());
		assertNull(pessoa);
	}
	
}
