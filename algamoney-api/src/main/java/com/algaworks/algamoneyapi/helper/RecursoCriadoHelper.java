package com.algaworks.algamoneyapi.helper;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class RecursoCriadoHelper {
	
	/**
	 * 
	 * Método utilitário responsável por adicionar a informação de "location" ao header de uma determinada requisição.
	 * O "location" identifica o caminho de acesso à um determinado recurso.
	 * 
	 * @param codigo - identificador único do recurso.
	 * @param resource - corpo retornado na requisição.
	 * @return
	 */
	public ResponseEntity<Object> resourceCreate(Long codigo, Object resource) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(codigo).toUri();
		return ResponseEntity.created(uri).body(resource);
	}
}
