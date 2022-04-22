package com.example.task.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.task.modelo.Departamento;
import com.example.task.modelo.Pessoa;
import com.example.task.modelo.Tarefa;

public class TarefaForm {
	
	@NotNull @NotEmpty @Size(min=2, max=40)
	private String titulo;
	@NotNull @NotEmpty @Size(min=2, max=80)
	private String descricao;
	@NotNull
	private LocalDate prazo;
	@NotNull
	private Long idDepartamento;
	@NotNull
	private Integer duracao;
	private Long idPessoa;
	private Boolean finalizado;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Tarefa converter(Pessoa pessoa, Departamento departamento) {
		return new Tarefa(titulo, descricao, prazo, departamento, duracao, pessoa, (finalizado == null ? false : finalizado));
	}
	
}
