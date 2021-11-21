package com.wproject.livrariaapi.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.wproject.livrariaapi.model.Perfil;
import com.wproject.livrariaapi.model.Usuario;
import com.wproject.livrariaapi.repository.PerfilRepository;
import com.wproject.livrariaapi.repository.UsuarioRepository;
import com.wproject.livrariaapi.service.TokenService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class LivroControllerTest {

	Usuario usuario;
	String token;
	
	@Autowired
	private TokenService service;

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeEach
	private void gerarToken() {
		this.usuario = new Usuario("WIllian", "willtet", "123456");
		Perfil perfil = perfilRepository.findById(1l).get();
		usuario.adicionarPerfil(perfil);
		usuarioRepository.save(usuario);
		
		
		Authentication auth = new UsernamePasswordAuthenticationToken(usuario , usuario.getLogin());
		this.token = service.gerarToken(auth);
	}
	
	@Test
	void naoDeveriaCadastrarLivroComDadosIncompletos() throws Exception {
		String json = "{}";
		mvc.perform(
				MockMvcRequestBuilders.post("/livro")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.header("Authorization", "Bearer "+ this.token))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	void naoDeveriaCadastrarLivroSemUsuariosNoBanco() throws Exception {
		//somente com dados em banco de dados
		String json = "{"
				+ "\"titulo\": \"A volta dos que não foram\","
				+ "\"numeroPagina\": \"100\","
				+ "\"lancamento\": \"2020-12-10\","
				+ "\"autor_id\": 71"
				+ "}";
		mvc.perform(
				MockMvcRequestBuilders.post("/livro")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.header("Authorization", "Bearer "+ this.token))
			.andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}

}
