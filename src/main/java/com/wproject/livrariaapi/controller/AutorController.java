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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.wproject.livrariaapi.dto.AutorFormAtualizarDto;
import com.wproject.livrariaapi.dto.AutorFormDto;
import com.wproject.livrariaapi.model.Autor;
import com.wproject.livrariaapi.service.AutorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/autor")
@Api(tags = "Autor")
public class AutorController {
	
	@Autowired
	private AutorService service;
	
	@GetMapping
	@ApiOperation("Listar todos os Autores")
	public Page<AutorDto> listar(@PageableDefault(size=20) Pageable paginacao){
		return service.listar(paginacao);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Listar Autor por id")
	public AutorDto listarPorId(@PathVariable Long id){
		return service.listarPorId(id);
	}
	
	@PostMapping
	@ApiOperation("Cadastrar Autor")
	public ResponseEntity<AutorDto> inserir(
			@RequestBody @Valid AutorFormDto autor,
			UriComponentsBuilder builder){
		AutorDto autorDto = service.inserir(autor);
		URI uri = builder
				.path("/autor/{id}")
				.buildAndExpand(autorDto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(autorDto);
	}
	
	@PutMapping
	@ApiOperation("Atualizar Autor")
	public ResponseEntity<AutorDto> atualizar(
			@RequestBody @Valid AutorFormAtualizarDto dto) {
		AutorDto autor = service.atualizar(dto);
		return ResponseEntity.ok(autor);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Deletar Autor por id")
	public ResponseEntity<AutorDto> deletar(
			@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
