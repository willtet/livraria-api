package com.wproject.livrariaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wproject.livrariaapi.dto.LoginFormDto;
import com.wproject.livrariaapi.dto.TokenDto;
import com.wproject.livrariaapi.infra.security.AutenticacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
@Api(tags = "Autenticação")
public class AutenticacaoController {

	@Autowired
	private AutenticacaoService service;
	
	@PostMapping
	@ApiOperation("Recuperar token de acesso")
	public TokenDto autenticar(@RequestBody LoginFormDto dto) {
		return new TokenDto(service.autenticar(dto));
		
	}
}
