package com.wproject.livrariaapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wproject.livrariaapi.dto.AutorDto;
import com.wproject.livrariaapi.dto.AutorFormDto;
import com.wproject.livrariaapi.dto.LivroDto;
import com.wproject.livrariaapi.dto.LivroFormDto;
import com.wproject.livrariaapi.model.Autor;
import com.wproject.livrariaapi.service.AutorService;
import com.wproject.livrariaapi.service.LivroService;

@RestController
@RequestMapping("/livro")
public class LivroController {
	
	@Autowired
	private LivroService service;
	
	@GetMapping
	public List<LivroDto> listar(){
		return service.listar();
	}
	
	@PostMapping
	public void inserir(@RequestBody @Valid LivroFormDto livro){
		service.inserir(livro);
	}
	
	@PutMapping
	public void atualizar(int id) {
		//a implementar
	}
	
	@DeleteMapping
	public void deletar(int id) {
		//a implementar
	}
	
	
}
