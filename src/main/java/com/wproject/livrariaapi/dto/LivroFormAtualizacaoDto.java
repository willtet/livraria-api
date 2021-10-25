package com.wproject.livrariaapi.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroFormAtualizacaoDto extends LivroFormDto{
	private Long id;
}
