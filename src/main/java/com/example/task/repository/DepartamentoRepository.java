package com.example.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.task.controller.dto.DepartamentoDetalhesDto;
import com.example.task.modelo.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	@Query("select new com.example.task.controller.dto.DepartamentoDetalhesDto(d.titulo,count(distinct p),count(t)) "
			+ "from Tarefa t "
			+ "left join t.departamento d "
			+ "left join t.pessoa p "
			+ "group by d.id "
			+ "order by d.titulo")
	List<DepartamentoDetalhesDto> lista();

}
