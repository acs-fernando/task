package com.example.task.controller.dto;

public class DepartamentoDetalhesDto {

	private String departamento;
	private long qtdPessoas;
	private long qtdTarefas;

	public DepartamentoDetalhesDto(String departamento, long qtdPessoas, long qtdTarefas) {
		this.departamento = departamento;
		this.qtdPessoas = qtdPessoas;
		this.qtdTarefas = qtdTarefas;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public long getQtdPessoas() {
		return qtdPessoas;
	}

	public void setQtdPessoas(long qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public long getQtdTarefas() {
		return qtdTarefas;
	}

	public void setQtdTarefas(long qtdTarefas) {
		this.qtdTarefas = qtdTarefas;
	}

}
