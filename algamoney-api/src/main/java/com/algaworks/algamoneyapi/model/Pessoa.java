package com.algaworks.algamoneyapi.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(position=1, value="${pessoa.codigo.docs.descricao}")
	private Long codigo;
	
	@NotNull
	@Size(min=3, max=80)
	@ApiModelProperty(position=2, value="${pessoa.nome.docs.descricao}")
	private String nome;
	
	@Embedded
	@Valid
	@ApiModelProperty(position=3, value="${pessoa.endereco.docs.descricao}")
	private Endereco endereco;
	
	@NotNull
	@ApiModelProperty(position=4, value="${pessoa.ativo.docs.descricao}")
	private Boolean ativo;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
