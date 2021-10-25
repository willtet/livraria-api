package com.wproject.livrariaapi.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.wproject.livrariaapi.dto.AutorDto;
import com.wproject.livrariaapi.dto.AutorFormDto;
import com.wproject.livrariaapi.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

	@Mock
	private AutorRepository repository;
	
	@InjectMocks
	private AutorService service;
	
	@Test
	void deveCadastrarAutor() {
		AutorFormDto form = new AutorFormDto(
				"Willian",
				"willian@email.com",
				LocalDate.now(),
				"Curriculo legal");
		
		AutorDto dto = service.inserir(form);
		Mockito.verify(repository).save(Mockito.any());

		assertEquals(form.getEmail(),dto.getEmail());
	}

}
