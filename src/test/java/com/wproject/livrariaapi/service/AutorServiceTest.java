package com.wproject.livrariaapi.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.wproject.livrariaapi.dto.AutorDto;
import com.wproject.livrariaapi.dto.AutorFormDto;
import com.wproject.livrariaapi.model.Autor;
import com.wproject.livrariaapi.model.Usuario;
import com.wproject.livrariaapi.repository.AutorRepository;
import com.wproject.livrariaapi.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {
	
	@Mock
	private ModelMapper mapper;

	@Mock
	private AutorRepository repository;
	
	@Mock
	private UsuarioRepository userRepository;
	
	@InjectMocks
	private AutorService service;
	
	private Usuario logado;
	
	@BeforeEach
	public void criarUsuario() {
		this.logado = new com.wproject.livrariaapi.model.Usuario("Willian", "willtet", "123456");
	}
	
	@Test
	void deveCadastrarAutor() {
		AutorFormDto form = new AutorFormDto(
				"Willian",
				"willian@email.com",
				LocalDate.now(),
				"Curriculo legal");
		
		Autor autor = new Autor(form.getNome(), form.getEmail(), form.getNascimento(), form.getCurriculo());
		Mockito.when(mapper.map(form,Autor.class)).thenReturn(autor);
		Mockito.when(mapper.map(autor,AutorDto.class)).thenReturn(new AutorDto(null, autor.getNome(), autor.getEmail(),autor.getCurriculo() , autor.getNascimento()));
		
		AutorDto dto = service.inserir(form);
		Mockito.verify(repository).save(Mockito.any());
		
		assertEquals(form.getEmail(),dto.getEmail());
	}

}
