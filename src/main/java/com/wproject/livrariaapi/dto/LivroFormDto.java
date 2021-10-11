package com.wproject.livrariaapi.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.wproject.livrariaapi.model.Autor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroFormDto {
	/* Título deve ser obrigatório e ter no mínimo 10 caracteres;
		Data de lançamento deve ser uma data menor ou igual a data atual;
    	O número de páginas deve ser maior ou igual a 100.
	 */
	
	@NotNull
	@Size(min = 10)
	private String titulo;
	
	@NotNull
	@PastOrPresent
	private LocalDate lancamento;
	
	@NotNull
	@Min(100)
	@JsonAlias("paginas")
	private int numeroPagina;
	
	
	@NotNull
	@JsonAlias("autor_id")
	private Long autorId;
}
