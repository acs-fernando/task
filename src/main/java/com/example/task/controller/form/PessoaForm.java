package com.example.task.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.task.modelo.Departamento;
import com.example.task.modelo.Pessoa;
import com.example.task.repository.PessoaRepository;

public class PessoaForm {
	
	@NotNull @NotEmpty @Size(min=2, max=40)
	private String nome;
	@NotNull
	private Long idDepartamento;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Pessoa converter(Departamento departamento) {
		return new Pessoa(nome, departamento);
	}
	
	public Pessoa atualizar(Long id, PessoaRepository pessoaRepository, Departamento departamento) {
		Pessoa pessoa = pessoaRepository.getById(id);
		pessoa.setNome(this.nome);
		pessoa.setDepartamento(departamento);
		return new Pessoa(nome, departamento);
	}

}
