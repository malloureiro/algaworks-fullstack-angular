package com.algaworks.algamoneyapi.helper;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class RecursoCriadoHelper {
	
	public ResponseEntity<?> resourceCreate(Long codigo, Object resource) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(codigo).toUri();
		return ResponseEntity.created(uri).body(resource);
	}
}
