package com.algaworks.algamoneyapi.service.integration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.algamoneyapi.model.HelloMessage;
import com.algaworks.algamoneyapi.service.impl.MockRemoteApiServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MockRemoteServiceIntegrationTests {

	@Autowired
	private MockRemoteApiServiceImpl remoteService;
	
	@Test
	public void testRemoteCall() {
		HelloMessage helloMessage = remoteService.remoteApiCall();
		assertThat(helloMessage).isNotNull();
		assertThat(helloMessage.getMsg()).isEqualTo("Hello World.");
	}
	
}
