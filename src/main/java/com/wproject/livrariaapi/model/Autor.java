package com.wproject.livrariaapi.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
	private String nome;
	private String email;
	private LocalDate nascimento;
	private String curriculo;
}
