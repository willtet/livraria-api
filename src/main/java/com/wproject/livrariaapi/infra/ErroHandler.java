package com.wproject.livrariaapi.infra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wproject.livrariaapi.dto.erro.Erro400Dto;
import com.wproject.livrariaapi.dto.erro.Erro500Dto;

@RestControllerAdvice
public class ErroHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<Erro400Dto> erro400handler(MethodArgumentNotValidException error){
		return error
				.getFieldErrors()
				.stream()
				.map(erro -> new Erro400Dto(erro.getField(), erro.getDefaultMessage()))
				.collect(Collectors.toList());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Erro500Dto erro500handler(Exception ex, HttpServletRequest req) {
		return new Erro500Dto(
				LocalDateTime.now(), 
				ex.getClass().toString(), 
				ex.getMessage(), 
				req.getRequestURI());
	}
	
	@ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public void tratarErro404(Exception ex, HttpServletRequest req) {
	}
}
