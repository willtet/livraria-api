package com.wproject.livrariaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuantidadePublicadoDto {
	private String nome;
	private Long quantidadeLivros;
	private Double percentual;
}
