package com.wproject.livrariaapi.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.wproject.livrariaapi.dto.LivroDto;
import com.wproject.livrariaapi.dto.LivroFormDto;
import com.wproject.livrariaapi.model.Autor;
import com.wproject.livrariaapi.model.Livro;
import com.wproject.livrariaapi.repository.AutorRepository;
import com.wproject.livrariaapi.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {
	
	@Mock
	private LivroRepository repository;
	
	@Mock
	private ModelMapper mapper;
	
	@Mock
	private AutorRepository autorRepository;
	
	@InjectMocks
	private LivroService service;
	
	

	@Test
	void deveCadastrarLivro() {
		LivroFormDto form = new LivroFormDto("A volta dos que nao foram", LocalDate.now(), 100, 1L);
		//Autor autor = autorRepository.findById(form.getAutorId()).get();
		Optional<Autor> autor = Optional.ofNullable(new Autor(form.getAutorId(), "oiiiii", "oiiiiii", LocalDate.now(),"okokokokok"));
		Mockito.when(autorRepository.findById(Mockito.anyLong())).thenReturn(autor);
		Livro livro = new Livro(form.getTitulo(), form.getLancamento(), form.getNumeroPagina(), autor.get());
		Mockito.when(mapper.map(form,Livro.class)).thenReturn(livro);
		Mockito.when(mapper.map(livro,LivroDto.class)).thenReturn(new LivroDto(null, livro.getTitulo(), livro.getLancamento(), livro.getNumeroPagina(), livro.getAutor()));

		LivroDto dto = service.inserir(form);
		Mockito.verify(repository).save(Mockito.any());
		
		
		assertEquals(form.getTitulo(),dto.getTitulo());
		
	}
	
	@Test
	void naoDeveriaCadastrarLivroSemTerAutorCadastrado() {
		LivroFormDto form = new LivroFormDto("A volta dos que nao foram", LocalDate.now(), 100, 1111L);
	
		Mockito
		.when(autorRepository.findById(Mockito.anyLong()))
		.thenThrow(EntityNotFoundException.class);
				
		assertThrows(IllegalArgumentException.class, () -> service.inserir(form));
	}
	

}
