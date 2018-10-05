package com.algaworks.algamoneyapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoneyapi.event.RecursoCriadoEvent;
import com.algaworks.algamoneyapi.model.Lancamento;
import com.algaworks.algamoneyapi.repository.LancamentoRepository;
import com.algaworks.algamoneyapi.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ApplicationEventPublisher eventPubliser;
	
	@Autowired
	private LancamentoService lancamentoService;

	@GetMapping
	public ResponseEntity<List<Lancamento>> listar() {
		List<Lancamento> lancamentos = lancamentoRepository.findAll();
		
		if (!lancamentos.isEmpty()) {
			return ResponseEntity.ok(lancamentos);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> listarPorCodigo(@PathVariable Long codigo) {
		
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		
		if (lancamento != null) {
			return ResponseEntity.ok(lancamento );
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> salvar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		
		Lancamento lancamentoSalvo = lancamentoService.salvarLancamento(lancamento);
		
		if (lancamentoSalvo != null) {
			eventPubliser.publishEvent(new RecursoCriadoEvent(lancamentoSalvo, lancamentoSalvo.getCodigo(), response));
			return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
		}
		
		return ResponseEntity.notFound().build();
	}
}
