package com.wproject.livrariaapi.dto;

import java.time.LocalDate;

import com.wproject.livrariaapi.model.Autor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDto {
	private String titulo;
	private LocalDate lancamento;
	private int numeroPagina;
	private Autor autor;
}
