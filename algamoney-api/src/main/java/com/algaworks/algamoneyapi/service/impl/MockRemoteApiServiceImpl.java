package com.algaworks.algamoneyapi.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.algaworks.algamoneyapi.model.HelloMessage;

/**
 * Service criado apenas para realizar chamada à uma API externa via RestTemplate e
 * possibilitar a criação de testes unitário e de integração.
 * 
 * REST MOCK criado com https://www.mockable.io
 * 
 * @author Mariana
 *
 */
@Service
public class MockRemoteApiServiceImpl {

	private final String URL = "http://demo4952478.mockable.io/mydata";
	
	public HelloMessage remoteApiCall() {
		return new RestTemplate().getForEntity(URL, HelloMessage.class).getBody();
	}
	
}
