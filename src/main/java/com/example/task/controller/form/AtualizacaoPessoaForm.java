package com.example.task.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.task.modelo.Pessoa;
import com.example.task.repository.PessoaRepository;

public class AtualizacaoPessoaForm {
	@NotNull @NotEmpty @Size(min=2, max=40)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Pessoa atualizar(Long id, PessoaRepository pessoaRepository) {
		Pessoa pessoa = pessoaRepository.getById(id);
		pessoa.setNome(nome);
		return pessoa;
	}
}
