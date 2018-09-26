package com.algaworks.algamoneyapi.service.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;

import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.repository.PessoaRepository;
import com.algaworks.algamoneyapi.service.impl.PessoaServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class PessoaServiceUnitTests {
	
	@Mock
	private PessoaRepository pessoaRepository;
	
	@InjectMocks
	private PessoaServiceImpl pessoaService;
	
	private Pessoa mockPessoa;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		mockPessoa = new Pessoa();
		mockPessoa.setNome("Mariana");
		mockPessoa.setAtivo(true);
	}
	
	@Test
	public void testInserirNovaPessoa() {
		
		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(mockPessoa);
		
		Pessoa novaPessoa = pessoaService.salvar(null);
		
		assertEquals(mockPessoa.getNome(), novaPessoa.getNome());
	}
	
	@Test
	public void testBuscarPessoaPorCodigo() {
		
		when(pessoaRepository.findOne(anyLong())).thenReturn(mockPessoa);
		
		Long mockId = null;
		Pessoa pessoa = pessoaService.buscarPessoaPorCodigo(mockId);
		
		assertEquals(mockPessoa.getNome(), pessoa.getNome());
		
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void testBuscarPessoaPorCodigoInexistente() {
		
		when(pessoaRepository.findOne(anyLong())).thenThrow(new EmptyResultDataAccessException(1));
		
		Long mockId = null;
		Pessoa pessoa = pessoaService.buscarPessoaPorCodigo(mockId);
		
		assertNull(pessoa);
		
	}

}
