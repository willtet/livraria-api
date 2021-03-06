package com.wproject.livrariaapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutorDto {
	
	private Long id;
	private String nome;
	private String email;
	private String curriculo;
	private LocalDate nascimento;
}
