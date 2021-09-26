package com.wproject.livrariaapi.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
	private String titulo;
	private LocalDate lancamento;
	private int numeroPagina;
	private Autor autor;
}
