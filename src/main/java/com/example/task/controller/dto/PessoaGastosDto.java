package com.example.task.controller.dto;

public class PessoaGastosDto {
	
	private String nome;
	private double mediaHorasGastas;
	
	public PessoaGastosDto(String nome, double mediaHorasGastas) {
		this.nome = nome;
		this.mediaHorasGastas = mediaHorasGastas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getMediaHorasGastas() {
		return mediaHorasGastas;
	}

	public void setMediaHorasGastas(double mediaHorasGastas) {
		this.mediaHorasGastas = mediaHorasGastas;
	}
	
}
