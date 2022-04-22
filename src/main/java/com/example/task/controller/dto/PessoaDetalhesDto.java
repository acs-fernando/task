package com.example.task.controller.dto;

import org.springframework.data.domain.Page;

import com.example.task.modelo.Pessoa;
import com.example.task.modelo.Tarefa;

public class PessoaDetalhesDto {
	
	private String nome;
	private String departamento;
	private Integer totalHoras;
	
	public PessoaDetalhesDto(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.departamento = pessoa.getDepartamento().getTitulo();
		this.totalHoras = pessoa.getTarefas() != null ? pessoa.getTarefas().stream().mapToInt(Tarefa::getDuracao).sum() : 0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Integer getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(Integer totalHoras) {
		this.totalHoras = totalHoras;
	}
	
	public static Page<PessoaDetalhesDto> converter(Page<Pessoa> pessoas) {
		return pessoas.map(PessoaDetalhesDto::new);
	}
	
}
