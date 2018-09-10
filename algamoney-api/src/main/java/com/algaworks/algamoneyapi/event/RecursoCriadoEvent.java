package com.algaworks.algamoneyapi.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * Este evento foi criado para adicionar o header "location" à resposta gerada na criação de um novo recurso.
 * 
 * <p>O objetivo da criação deste evento é demonstrar a implementação de eventos com a utilização das classes:
 * ApplicationEventListener e ApplicationEventPublisher.
 * 
 * <p>Para este caso de uso específico, uma outra maneira mais simples de realizar a alteração do header (no response)
 * foi feita em: {@link com.algaworks.algamoneyapi.helper.RecursoCriadoHelper}
 * 
 * @author mloureiro
 *
 */
public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private HttpServletResponse response;
	
	public RecursoCriadoEvent(Object source, Long codigo, HttpServletResponse response) {
		super(source);
		this.codigo = codigo;
		this.response = response;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
}
