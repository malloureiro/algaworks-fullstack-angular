package com.algaworks.algamoneyapi.exceptionhandler;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private String mensagem;
	private String erro;

	public ApiError(HttpStatus status, String mensagem, String erro) {
		this.status = status;
		this.mensagem = mensagem;
		this.erro = erro;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
	
}
