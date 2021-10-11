package com.wproject.livrariaapi.service;


import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wproject.livrariaapi.dto.AutorDto;
import com.wproject.livrariaapi.dto.AutorFormDto;
import com.wproject.livrariaapi.model.Autor;
import com.wproject.livrariaapi.repository.AutorRepository;

@Service
public class AutorService {
	@Autowired
	private AutorRepository repository;
	private ModelMapper modelMapper = new ModelMapper();
	
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
	
	
}
