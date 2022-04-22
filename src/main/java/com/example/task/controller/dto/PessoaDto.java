package com.example.task.controller.dto;

import org.springframework.data.domain.Page;

import com.example.task.modelo.Pessoa;

public class PessoaDto {

	private Long id;
	private String nome;

	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static Page<PessoaDto> converter(Page<Pessoa> pessoas) {
		return pessoas.map(PessoaDto::new);
	}

}
