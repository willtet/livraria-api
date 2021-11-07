package com.wproject.livrariaapi.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

import com.wproject.livrariaapi.dto.UsuarioDto;
import com.wproject.livrariaapi.dto.UsuarioFormAtualizarDto;
import com.wproject.livrariaapi.dto.UsuarioFormDto;
import com.wproject.livrariaapi.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuario")
@Api(tags = "Usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	@ApiOperation("Listar todos os usuarios")
	public Page<UsuarioDto> listarTodos(@PageableDefault(size=20) Pageable paginacao){
		return service.lerTodos(paginacao);
	}
	
	@PostMapping
	@ApiOperation("Cadastrar novos usuarios")
	public ResponseEntity<UsuarioDto> cadastrar(
			@RequestBody @Valid UsuarioFormDto dto,
			UriComponentsBuilder builder
			){
		UsuarioDto usuarioDto = service.cadastrar(dto);
		URI uri = builder
				.path("/usuario/{id}")
				.buildAndExpand(usuarioDto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioDto);
	}
	
	@PutMapping
	@ApiOperation("Atualizar dados dos usuarios")
	public ResponseEntity<UsuarioDto> atualizar(@RequestBody @Valid UsuarioFormAtualizarDto form) {
		UsuarioDto usuario = service.atualizar(form);
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Deletar usuario por id")
	public ResponseEntity<UsuarioDto> deletar(@PathVariable @NotNull Long id){
		service.remover(id);
		
		return ResponseEntity.noContent().build();
	}

}
