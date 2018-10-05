package com.algaworks.algamoneyapi.service.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.algaworks.algamoneyapi.model.HelloMessage;
import com.algaworks.algamoneyapi.service.impl.MockRemoteApiServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
public class MockRemoteServiceUnitTests {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	@Spy
	private MockRemoteApiServiceImpl remoteApiService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetRemoteApiContent() {
		
		HelloMessage messageBody = new HelloMessage();
		messageBody.setMsg("Hello World");
	
		when(restTemplate.getForEntity(anyString(), any())).thenReturn(new ResponseEntity<Object>(messageBody, HttpStatus.OK));
		
		HelloMessage message = remoteApiService.remoteApiCall();
		assertThat(message).isNotNull();
		assertThat(message.getMsg()).isEqualTo(messageBody.getMsg());
		
	}
	
}