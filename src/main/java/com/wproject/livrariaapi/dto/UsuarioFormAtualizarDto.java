package com.wproject.livrariaapi.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFormAtualizarDto extends UsuarioFormDto{
	
	@NotNull
	private Long id;
}