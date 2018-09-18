package com.algaworks.algamoneyapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoneyapi.helper.RecursoCriadoHelper;
import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.repository.PessoaRepository;
import com.algaworks.algamoneyapi.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	/*@Autowired
	private ApplicationEventPublisher eventPublisher;
	*/
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		
		if (pessoas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping(path="/{codigo}", produces="application/json")
	public ResponseEntity<Pessoa> listarPorCodigo(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		
		if (pessoa != null) {
			return ResponseEntity.ok(pessoa);
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(produces="application/json")
	public ResponseEntity<?> salvar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa novaPessoa = pessoaRepository.save(pessoa);
		
		//eventPublisher.publishEvent(new RecursoCriadoEvent(this, novaPessoa.getCodigo(), response));
		return new RecursoCriadoHelper().resourceCreate(novaPessoa.getCodigo(), novaPessoa);
	}
	
	@DeleteMapping(path="/{codigo}", produces="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.delete(codigo);
	}
	
	@PutMapping(path="/{codigo}", produces="application/json")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaExistente = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaExistente);
	}
	
	@PutMapping(path="/{codigo}/ativo", produces="application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
		
	}

}
