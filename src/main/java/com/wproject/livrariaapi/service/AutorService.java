package com.wproject.livrariaapi.service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.wproject.livrariaapi.dto.AutorDto;
import com.wproject.livrariaapi.dto.AutorFormAtualizarDto;
import com.wproject.livrariaapi.dto.AutorFormDto;
import com.wproject.livrariaapi.model.Autor;
import com.wproject.livrariaapi.repository.AutorRepository;

@Service
public class AutorService {
	@Autowired
	private AutorRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<AutorDto> listar(Pageable page){
		Page<Autor> autores = repository.findAll(page);
		return autores.map(autor -> modelMapper.map(autor, AutorDto.class));
	}

	@Transactional
	public AutorDto inserir(AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		repository.save(autor);
		return modelMapper.map(autor, AutorDto.class);
	}

	@Transactional
	public AutorDto atualizar(AutorFormAtualizarDto dto) {
		Autor autor = repository.getById(dto.getId());
		autor.atualizar(
				dto.getNome(),
				dto.getEmail(),
				dto.getNascimento(),
				dto.getCurriculo()
				);
		return modelMapper.map(autor, AutorDto.class);
	}

	public void deletar(Long id) {
		repository.deleteById(id);		
	}

	public AutorDto listarPorId(Long id) {
		Autor autor = repository.findById(id).orElseThrow(()->new EntityNotFoundException());
		return modelMapper.map(autor, AutorDto.class);
	}
	
	
	
}
