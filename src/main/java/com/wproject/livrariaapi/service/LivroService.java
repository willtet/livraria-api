package com.wproject.livrariaapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.wproject.livrariaapi.dto.LivroDto;
import com.wproject.livrariaapi.dto.LivroFormDto;
import com.wproject.livrariaapi.model.Livro;
import com.wproject.livrariaapi.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	private LivroRepository repository;
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<LivroDto> listar(Pageable page){
		Page<Livro> livros = repository.findAll(page);
		return livros.map(livro -> modelMapper.map(livro, LivroDto.class));
	}

	@Transactional
	public LivroDto inserir(LivroFormDto autor) {
		Livro livro = modelMapper.map(autor, Livro.class);
		livro.setId(null);
		repository.save(livro);
		return modelMapper.map(livro, LivroDto.class);
	}
			
}
