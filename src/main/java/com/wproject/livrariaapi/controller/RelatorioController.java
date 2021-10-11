package com.wproject.livrariaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wproject.livrariaapi.dto.QuantidadePublicadoDto;
import com.wproject.livrariaapi.service.RelatorioService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {
	
	@Autowired
	private RelatorioService service;
	
	@GetMapping("/livros")
	public List<QuantidadePublicadoDto> listarRelatorioLivros(){
		return service.listarRelatorio();
	}
}
