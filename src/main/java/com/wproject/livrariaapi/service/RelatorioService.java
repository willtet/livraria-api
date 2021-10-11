package com.wproject.livrariaapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.wproject.livrariaapi.dto.QuantidadePublicadoDto;
import com.wproject.livrariaapi.repository.LivroRepository;

@Service
public class RelatorioService {
	
	@Autowired
	private LivroRepository repository;

	public List<QuantidadePublicadoDto> listarRelatorio() {
		return repository.listarRelatorioQuantidadeLivro();
	}
}
