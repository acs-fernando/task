package com.example.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task.modelo.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	List<Tarefa> findTop3ByPessoaIsNullOrderByPrazoAsc();

}
