package com.algaworks.algamoneyapi.exceptionhandler;

public class ApiError {

	private String mensagem;
	private String erro;

	public ApiError(String mensagem, String erro) {
		this.mensagem = mensagem;
		this.erro = erro;
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
