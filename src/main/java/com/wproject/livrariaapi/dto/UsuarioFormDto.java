package com.wproject.livrariaapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFormDto {
	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	@NotEmpty
	private String login;
	
	@NotNull
	@NotEmpty
	private String senha;
	
	@NotNull
	private Long perfilId;
	
	@NotBlank
	private String email;
}