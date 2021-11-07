package com.wproject.livrariaapi.infra.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wproject.livrariaapi.model.Usuario;
import com.wproject.livrariaapi.repository.UsuarioRepository;
import com.wproject.livrariaapi.service.TokenService;

public class VerificarFilterToken extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	

	public VerificarFilterToken(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		if(token == null || token.trim().isEmpty()) {
			System.out.print("aqui1");
			filterChain.doFilter(request, response);
			return;
		}
		
		token = token.replace("Bearer ", "");
		System.out.print("aqui2");
		boolean tokenValid = tokenService.isValid(token);
		
		if (tokenValid) {
			Long idUsuario = tokenService.extrairId(token);
			Usuario logado = usuarioRepository.carregarIdComPerfil(idUsuario).get();
			
			Authentication auth = new UsernamePasswordAuthenticationToken(logado,  null, logado.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
		
	}

}
