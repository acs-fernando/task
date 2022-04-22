package com.example.task.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.task.controller.form.TarefaForm;
import com.example.task.modelo.Departamento;
import com.example.task.modelo.Pessoa;
import com.example.task.modelo.Tarefa;
import com.example.task.repository.DepartamentoRepository;
import com.example.task.repository.PessoaRepository;
import com.example.task.repository.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<String> cadastrar(@RequestBody @Valid TarefaForm form, UriComponentsBuilder uriBuilder) {
		StringBuilder erros = new StringBuilder();
		Optional<Departamento> departamento = departamentoRepository.findById(form.getIdDepartamento());
		Optional<Pessoa> pessoa = Optional.empty();
		if(form.getIdPessoa() != null) {
			pessoa = pessoaRepository.findById(form.getIdPessoa());
			if(!pessoa.isPresent()) erros.append("A pessoa (id) informada não existe na base de dados\n");
		}
		if(!departamento.isPresent()) erros.append("O departamento (id) informado não existe na base de dados");
		if(erros.length() > 0)
			return ResponseEntity.badRequest().body(erros.toString());
		else {
			Tarefa tarefa = form.converter(pessoa.orElse(null), departamento.get());
			tarefaRepository.save(tarefa);
			URI uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
	}
	
	@PutMapping("/alocar/{id}")
	@Transactional
	public ResponseEntity<String> alocar(@PathVariable Long id, @RequestParam Long idPessoa) {
		StringBuilder erros = new StringBuilder();
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
		if(!tarefa.isPresent()) erros.append("Não há tarefa cadastrada com o id informado");
		if(!pessoa.isPresent()) erros.append("Não há pessoa cadastrada com o id informado");
		if(tarefa.isPresent() && pessoa.isPresent()) {
			if(!tarefa.get().getDepartamento().getId().equals(pessoa.get().getDepartamento().getId()))
				erros.append("A pessoa não pertence ao mesmo departamento relacionado à tarefa");
		}
		if(erros.length() > 0) 
			return ResponseEntity.badRequest().body(erros.toString());
		
		Tarefa tarefaBase = tarefaRepository.getById(tarefa.get().getId());
		tarefaBase.setPessoa(pessoa.get());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/finalizar/{id}")
	@Transactional
	public ResponseEntity<String> finalizar(@PathVariable Long id) {
		StringBuilder erros = new StringBuilder();
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		if(!tarefa.isPresent()) erros.append("Não há tarefa cadastrada com o id informado");
		if(erros.length() > 0) 
			return ResponseEntity.badRequest().body(erros.toString());
		
		Tarefa tarefaBase = tarefaRepository.getById(tarefa.get().getId());
		tarefaBase.setFinalizado(true);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/pendentes")
	public List<Tarefa> pendentes(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		return tarefaRepository.findTop3ByPessoaIsNullOrderByPrazoAsc();
	}
	
}
