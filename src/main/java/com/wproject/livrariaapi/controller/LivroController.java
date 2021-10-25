package com.wproject.livrariaapi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wproject.livrariaapi.dto.AutorDto;
import com.wproject.livrariaapi.dto.AutorFormDto;
import com.wproject.livrariaapi.dto.LivroDto;
import com.wproject.livrariaapi.dto.LivroFormAtualizacaoDto;
import com.wproject.livrariaapi.dto.LivroFormDto;
import com.wproject.livrariaapi.model.Autor;
import com.wproject.livrariaapi.service.AutorService;
import com.wproject.livrariaapi.service.LivroService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/livro")
@Api(tags = "Livro")
public class LivroController {
	
	@Autowired
	private LivroService service;
	
	@GetMapping
	public Page<LivroDto> listar(@PageableDefault(size = 10) Pageable page){
		return service.listar(page);
	}
	
	@GetMapping("/{id}")
	public LivroDto listarPorId(@PathVariable Long id){
		return service.listarPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<LivroDto> inserir(
			@RequestBody @Valid LivroFormDto livro,
			UriComponentsBuilder builder){
		LivroDto dto = service.inserir(livro);
		URI uri = builder
				.path("/autor/{id}")
				.buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping
	public ResponseEntity<LivroDto> atualizar(@RequestBody @Valid LivroFormAtualizacaoDto livro) {
		LivroDto livroDto = service.atualizar(livro);
		return ResponseEntity.ok(livroDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LivroDto> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
