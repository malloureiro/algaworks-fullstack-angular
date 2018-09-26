package com.algaworks.algamoneyapi.service.integration;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.service.PessoaService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@ActiveProfiles("test")
public class PessoaServiceIntegrationTests {

	@Autowired
	private PessoaService pessoaService;
	
	private Pessoa aPessoa;
	
	private Pessoa pessoaSalva;
	
	@Before
	public void setup() {
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
	
	@Test
	public void testAtivarInativarPessoa() {
		
		boolean ativo = false;
		
		Pessoa pessoaInativa = pessoaService.atualizarPropriedadeAtivo(pessoaSalva.getCodigo(), ativo);
		assertEquals(pessoaInativa.getAtivo(), ativo);
		
		ativo = true;
		
		Pessoa pessoaAtiva = pessoaService.atualizarPropriedadeAtivo(pessoaSalva.getCodigo(), ativo);
		assertEquals(pessoaAtiva.getAtivo(), ativo);
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
