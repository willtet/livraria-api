package com.wproject.livrariaapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;


import com.wproject.livrariaapi.dto.LivroDto;
import com.wproject.livrariaapi.dto.LivroFormAtualizacaoDto;
import com.wproject.livrariaapi.dto.LivroFormDto;
import com.wproject.livrariaapi.model.Autor;
import com.wproject.livrariaapi.model.Livro;
import com.wproject.livrariaapi.repository.AutorRepository;
import com.wproject.livrariaapi.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private AutorRepository repositoryAutor;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<LivroDto> listar(Pageable page){
		Page<Livro> livros = repository.findAll(page);
		return livros.map(livro -> modelMapper.map(livro, LivroDto.class));
	}

	@Transactional
	public LivroDto inserir(LivroFormDto livros) {
		try {
			Autor autor = repositoryAutor.findById(livros.getAutorId()).get();
			Livro livro = modelMapper.map(livros, Livro.class);
			livro.setId(null);
			livro.setAutor(autor);
			repository.save(livro);
			return modelMapper.map(livro, LivroDto.class);
			
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Autor inexistente");
		}
	}

	public LivroDto listarPorId(Long id) {
		Livro livro = repository.findById(id).orElseThrow(()->new EntityNotFoundException());
		return modelMapper.map(livro, LivroDto.class);
	}

	@Transactional
	public LivroDto atualizar(@Valid LivroFormAtualizacaoDto dto) {
		Livro livro = repository.getById(dto.getId());
		livro.atualizarInfo(
				dto.getTitulo(),
				dto.getLancamento(),
				dto.getNumeroPagina()
				);
		return modelMapper.map(livro, LivroDto.class);	}

	@Transactional
	public void deletar(Long id) {
		Autor a = repositoryAutor.getById(id);
		repository.deleteById(id);
	}
			
}
