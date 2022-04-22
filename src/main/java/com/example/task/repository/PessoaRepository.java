package com.example.task.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.task.controller.dto.PessoaGastosDto;
import com.example.task.modelo.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query("select new com.example.task.controller.dto.PessoaGastosDto(p.nome, avg(t.duracao)) "
			+ "from Pessoa p join p.tarefas t "
			+ "where lower(p.nome) like %:nome% and t.prazo between :begin and :end "
			+ "group by p.id "
			+ "order by 2")
	Page<PessoaGastosDto> busca(@Param("nome") String nome, @Param("begin") LocalDate dataInicio, 
			@Param("end") LocalDate dataFim, Pageable paginacao);

}
