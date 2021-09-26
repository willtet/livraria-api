package com.wproject.livrariaapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.wproject.livrariaapi.dto.LivroDto;
import com.wproject.livrariaapi.dto.LivroFormDto;
import com.wproject.livrariaapi.model.Livro;

@Service
public class LivroService {
	private List<Livro> livros = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<LivroDto> listar(){
		return livros.stream().map(livro -> modelMapper.map(livro, LivroDto.class)).collect(Collectors.toList());
	}

	public void inserir(LivroFormDto autor) {
		Livro livro = modelMapper.map(autor, Livro.class);
		livros.add(livro);
	}
			
}
