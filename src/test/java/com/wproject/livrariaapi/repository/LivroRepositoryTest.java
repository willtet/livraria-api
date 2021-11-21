package com.wproject.livrariaapi.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wproject.livrariaapi.dto.QuantidadePublicadoDto;
import com.wproject.livrariaapi.model.Autor;
import com.wproject.livrariaapi.model.Livro;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	void deveListarRelatorioQuantidadeLivroTendoDadosNoBanco() {
		Autor andre = new Autor("Andre da silva", "andre@email.com", LocalDate.parse("1980-01-01"), "Curriculo top");
		em.persist(andre);
		Livro andre1 = new Livro("Aprenda Java em 21 dias", LocalDate.parse("2004-03-12"), 100, andre);
		em.persist(andre1);
		Livro andre2 = new Livro("Aprenda Python em 21 dias", LocalDate.parse("2012-01-01"), 100, andre);
		em.persist(andre2);
		
		Autor fernanda = new Autor("Fernanda nogueira", "fernanda@email.com", LocalDate.parse("1980-01-01"), "Curriculo top");
		em.persist(fernanda);
		Livro fernanda1 = new Livro("Como ser mais produtivo", LocalDate.parse("2004-04-21"), 100, fernanda);
		em.persist(fernanda1);
		Livro fernanda2 = new Livro("Otimizando o seu tempo", LocalDate.parse("2004-04-21"), 100, fernanda);
		em.persist(fernanda2);
		
		Autor juliana = new Autor("Juliana Carvalho", "juliana@email.com", LocalDate.parse("1980-01-01"), "Curriculo top");
		em.persist(juliana);
		Livro juliana1 = new Livro("Aprenda a falar em publico", LocalDate.parse("2004-07-01"), 100, juliana);
		em.persist(juliana1);
		
		Autor rita = new Autor("Rita de Assis", "juliana@email.com", LocalDate.parse("1980-01-01"), "Curriculo top");
		em.persist(rita);
		Livro rita1 = new Livro("Como fazer bolos incriveis", LocalDate.parse("2004-07-01"), 100, rita);
		em.persist(rita1);
		
		Autor rodrigo = new Autor("Rodrigo de souza", "juliana@email.com", LocalDate.parse("1980-01-01"), "Curriculo top");
		em.persist(rodrigo);
		Livro rodrigo1 = new Livro("Investindo em ações de bolda de valores", LocalDate.parse("2004-07-01"), 100, rodrigo);
		em.persist(rodrigo1);
		
		
		List<QuantidadePublicadoDto> lista =  repository.listarRelatorioQuantidadeLivro();
		
		Assertions
		.assertThat(lista)
		.hasSize(5)
		.extracting(
				QuantidadePublicadoDto::getNome,
				QuantidadePublicadoDto::getQuantidadeLivros, 
				QuantidadePublicadoDto::getPercentual)
		.contains(
				Assertions.tuple("Andre da silva", 2L, 0.2857142857142857),
				Assertions.tuple("Fernanda nogueira", 2L, 0.2857142857142857),
				Assertions.tuple("Juliana Carvalho", 1L, 0.14285714285714285),
				Assertions.tuple("Rita de Assis", 1L, 0.14285714285714285),
				Assertions.tuple("Rodrigo de souza", 1L, 0.14285714285714285)
				);
		
	}

}
