package com.algaworks.algamoneyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoneyapi.model.HelloMessage;
import com.algaworks.algamoneyapi.service.impl.MockRemoteApiServiceImpl;

/**
 * Controller criado apenas para realizar chamada à uma API externa via RestTemplate e
 * possibilitar a criação de testes unitário e de integração.
 * 
 * @author Mariana
 *
 */
@RestController
@RequestMapping("/mockable")
public class MockRemoteApiController {
	
	@Autowired
	private MockRemoteApiServiceImpl remoteService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> helloWorld() {
		HelloMessage helloMessage = remoteService.remoteApiCall();
		return ResponseEntity.ok(helloMessage.getMsg());
	} 

}
