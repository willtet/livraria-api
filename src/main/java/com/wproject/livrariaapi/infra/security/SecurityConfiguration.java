package com.wproject.livrariaapi.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wproject.livrariaapi.repository.UsuarioRepository;
import com.wproject.livrariaapi.service.TokenService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacaoService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(service).passwordEncoder(encoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/auth").permitAll()
			.antMatchers("/usuario").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/autor").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/autor/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/autor").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/livro").hasRole("ADMIN")
			.antMatchers(HttpMethod.DELETE, "/livro/**").hasRole("ADMIN")
			.antMatchers(HttpMethod.PUT, "/livro").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().csrf().disable()
			.addFilterBefore(
					new VerificarFilterToken(tokenService, usuarioRepository),
					UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers(
				"/v2/api-docs",
				"/configuration/ui",
				"/swagger-resources/**",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**"
				);
	}

}
