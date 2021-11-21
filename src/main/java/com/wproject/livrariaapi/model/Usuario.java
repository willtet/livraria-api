package com.wproject.livrariaapi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"senha"})
@EqualsAndHashCode(of ="id")
@Table(name="usuarios")
@Entity
public class Usuario implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private String email;
	
	@ManyToMany
	@JoinTable(name = "perfis_usuarios",
			joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "perfil_id")
			)
	private List<Perfil> perfis = new ArrayList<>();
	
	
	
	public Usuario(String nome, String login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.getPerfis();
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void adicionarPerfil(Perfil perfil) {
		this.perfis.add(perfil);
	}
	
	public void atualizar(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
}
