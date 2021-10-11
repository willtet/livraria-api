package com.wproject.livrariaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wproject.livrariaapi.dto.QuantidadePublicadoDto;
import com.wproject.livrariaapi.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	@Query("select new com.wproject.livrariaapi.dto.QuantidadePublicadoDto("
			+ "a.nome,"
			+ "(select count(*) from Livro l where autor_id = a.id),"
			+ "(select count(*) from Livro l where autor_id = a.id) * 1.0 / (select count(*) from Livro l2)"
			+ ") from Autor a")
	List<QuantidadePublicadoDto> listarRelatorioQuantidadeLivro();

}
