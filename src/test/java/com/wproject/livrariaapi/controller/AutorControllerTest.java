package com.wproject.livrariaapi.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AutorControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	void naoDeveriaCadastrarUsuarioComDadosIncompletos() throws Exception {
		String json = "{}";
		mvc.perform(
				MockMvcRequestBuilders.post("/autor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void deveriaCadastrarUsuarioComDadoscompletos() throws Exception {
		String json = "{"
				+ "\"nome\": \"willian2\","
				+ "\"email\": \"email@email.com\","
				+ "\"nascimento\": \"1970-12-10\","
				+ "\"curriculo\": \"Curriculo bacana\""
				+ "}";
		mvc.perform(
				MockMvcRequestBuilders.post("/autor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.header().exists("Location"))
			.andExpect(MockMvcResultMatchers.content().json(json));
	}

}
