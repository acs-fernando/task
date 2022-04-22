package com.example.task.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.task.controller.dto.PessoaDetalhesDto;
import com.example.task.controller.dto.PessoaGastosDto;
import com.example.task.controller.form.AtualizacaoPessoaForm;
import com.example.task.controller.form.PessoaForm;
import com.example.task.modelo.Departamento;
import com.example.task.modelo.Pessoa;
import com.example.task.repository.DepartamentoRepository;
import com.example.task.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@GetMapping
	@Cacheable(value = "listaDePessoas")
	public Page<PessoaDetalhesDto> lista(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
		return PessoaDetalhesDto.converter(pessoas);
	}
	
	@GetMapping("/gastos")
	public Page<PessoaGastosDto> gastos(@RequestParam String nome, @RequestParam String dataInicio, @RequestParam String dataFim,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dtInicio = LocalDate.parse(dataInicio, formatter);
		LocalDate dtFim = LocalDate.parse(dataFim, formatter);
		Page<PessoaGastosDto> pessoas = pessoaRepository.busca(nome.toLowerCase(), dtInicio, dtFim, paginacao);
		return pessoas;
	}
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDePessoas", allEntries = true)
	public ResponseEntity<String> cadastrar(@RequestBody @Valid PessoaForm form, UriComponentsBuilder uriBuilder) {
		Optional<Departamento> departamento = departamentoRepository.findById(form.getIdDepartamento());
		if(!departamento.isPresent()) {
			return ResponseEntity.badRequest().body("O departamento (id) informado não existe na base de dados");
		}
		Pessoa pessoa = form.converter(departamento.get());
		pessoaRepository.save(pessoa);

		URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDePessoas", allEntries = true)
	public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoPessoaForm form, BindingResult bindingResult) {
		Optional<Pessoa> optional = pessoaRepository.findById(id);
		if(optional.isPresent()) {
			form.atualizar(id, pessoaRepository);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDePessoas", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Pessoa> optional = pessoaRepository.findById(id);
		if(optional.isPresent()) {
			pessoaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
