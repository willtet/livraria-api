package com.wproject.livrariaapi.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wproject.livrariaapi.dto.UsuarioDto;
import com.wproject.livrariaapi.dto.UsuarioFormAtualizarDto;
import com.wproject.livrariaapi.dto.UsuarioFormDto;
import com.wproject.livrariaapi.erro.UserAlreadyExistsError;
import com.wproject.livrariaapi.model.Perfil;
import com.wproject.livrariaapi.model.Usuario;
import com.wproject.livrariaapi.repository.PerfilRepository;
import com.wproject.livrariaapi.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private ModelMapper mapper;

	public Page<UsuarioDto> lerTodos(Pageable paginacao) {
		Page<Usuario> usuarios = repository.findAll(paginacao);
		return usuarios.map((usuario)-> mapper.map(usuario, UsuarioDto.class));
	}

	@Transactional
	public UsuarioDto cadastrar(@Valid UsuarioFormDto dto) {
		Usuario user = mapper.map(dto, Usuario.class);
		if (repository.existsByLogin(user.getLogin())) {
			throw new UserAlreadyExistsError("Usuario já existente");
		}
		
		user.setSenha(encoder.encode(dto.getSenha()));
		
		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		user.adicionarPerfil(perfil);
		
		repository.save(user);
		return mapper.map(user, UsuarioDto.class);
	}

	@Transactional
	public UsuarioDto atualizar(UsuarioFormAtualizarDto form) {
		Usuario usuario = repository.getById(form.getId());
		usuario.atualizar(
				form.getNome(),
				form.getSenha()
				);
		return mapper.map(usuario, UsuarioDto.class);
	}

	public void remover(@NotNull Long id) {
		repository.deleteById(id);
		
	}

}
