package br.com.spring.io.hateoas.spring_hateoas;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting extends ResourceSupport {
	
	private final String content;
	
	// Anotações que sinalizam ao Jackson JSON library como transformar este objeto em uma representação JSON
	
	@JsonCreator
	public Greeting(@JsonProperty("content") String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

}
