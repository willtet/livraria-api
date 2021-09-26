package com.wproject.livrariaapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.wproject.livrariaapi.dto.AutorDto;
import com.wproject.livrariaapi.dto.AutorFormDto;
import com.wproject.livrariaapi.model.Autor;

@Service
public class AutorService {
	private List<Autor> autores = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	
	public List<AutorDto> listar(){
		return autores.stream().map(autor -> modelMapper.map(autor, AutorDto.class)).collect(Collectors.toList());
	}

	public void inserir(AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		autores.add(autor);
	}
	
	
}
