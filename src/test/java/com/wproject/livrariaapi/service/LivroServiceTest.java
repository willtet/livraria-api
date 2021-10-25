package com.wproject.livrariaapi.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wproject.livrariaapi.dto.LivroDto;
import com.wproject.livrariaapi.dto.LivroFormDto;
import com.wproject.livrariaapi.repository.AutorRepository;
import com.wproject.livrariaapi.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {
	
	@Mock
	private LivroRepository repository;
	
	@Mock
	private AutorRepository autorRepository;
	
	@InjectMocks
	private LivroService service;

	@Test
	void deveCadastrarLivro() {
		LivroFormDto form = new LivroFormDto("A volta dos que nao foram", LocalDate.now(), 100, 1L);
		
		LivroDto dto = service.inserir(form);
		Mockito.verify(repository).save(Mockito.any());
		
		
		assertEquals(form.getTitulo(),dto.getTitulo());
		
	}
	
	@Test
	void naoDeveriaCadastrarLivroSemTerAutorCadastrado() {
		LivroFormDto form = new LivroFormDto("A volta dos que nao foram", LocalDate.now(), 100, 1111L);
	
		Mockito
		.when(autorRepository.getById(form.getAutorId()))
		.thenThrow(EntityNotFoundException.class);
				
		assertThrows(IllegalArgumentException.class, () -> service.inserir(form));
	}
	

}
