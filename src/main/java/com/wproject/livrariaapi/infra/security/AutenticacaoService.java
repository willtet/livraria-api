package com.wproject.livrariaapi.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wproject.livrariaapi.dto.LoginFormDto;
import com.wproject.livrariaapi.repository.UsuarioRepository;
import com.wproject.livrariaapi.service.TokenService;

@Service
public class AutenticacaoService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepositorio
					.findByLogin(username)
					.orElseThrow(()->new UsernameNotFoundException("Usuario não encontrado"));
	}
	
	public String autenticar(LoginFormDto loginDto) {
		Authentication auth = new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getSenha());
		auth= authenticationManager.authenticate(auth);
		
		return tokenService.gerarToken(auth);
	}

}
