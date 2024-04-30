package br.com.fiap.fintrack.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.fintrack.model.Categoria;
import br.com.fiap.fintrack.model.Movimentacao;
import br.com.fiap.fintrack.repository.CategoriaRepository;
import br.com.fiap.fintrack.repository.MovimentacaoRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Override
    public void run(String... args) throws Exception {

        categoriaRepository.saveAll(
                List.of(
                        Categoria.builder().id(1L).nome("educação").icone("book").build(),
                        Categoria.builder().id(2L).nome("transporte").icone("bus").build(),
                        Categoria.builder().id(3L).nome("lazer").icone("bus").build(),
                        Categoria.builder().id(4L).nome("alimentação").icone("apple").build()));

        movimentacaoRepository.saveAll(
                List.of(
                        Movimentacao.builder()
                                .id(1L)
                                .descricao("Luz")
                                .data(LocalDate.now())
                                .valor(new BigDecimal(100))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(3L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(2L)
                                .descricao("Cinema")
                                .data(LocalDate.now().minusDays(1))
                                .valor(new BigDecimal(80))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(3L)
                                .descricao("Mc Donalds")
                                .data(LocalDate.now())
                                .valor(new BigDecimal(90))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(4L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(4L)
                                .descricao("Aluguel")
                                .data(LocalDate.now())
                                .valor(new BigDecimal(1000))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(1L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(5L)
                                .descricao("Livros")
                                .data(LocalDate.now().minusDays(3))
                                .valor(new BigDecimal(250))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(1L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(6L)
                                .descricao("Água")
                                .data(LocalDate.now().minusWeeks(1))
                                .valor(new BigDecimal(50))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(7L)
                                .descricao("Mercado")
                                .data(LocalDate.now().minusWeeks(4))
                                .valor(new BigDecimal(400))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(3L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(8L)
                                .descricao("Mensalidade")
                                .data(LocalDate.now().minusMonths(2))
                                .valor(new BigDecimal(500))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(1L).get())
                                .build(),

                        Movimentacao.builder()
                                .id(9L)
                                .descricao("Mc Donalds")
                                .valor(new BigDecimal(55))
                                .data(LocalDate.now())
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(4L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(10L)
                                .descricao("Livros")
                                .valor(new BigDecimal(100))
                                .data(LocalDate.now().minusMonths(2))
                                .tipo("RECEITA")
                                .categoria(categoriaRepository.findById(3L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(11L)
                                .descricao("Cinema")
                                .valor(new BigDecimal(200))
                                .data(LocalDate.now().minusWeeks(8))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(12L)
                                .descricao("Futebol")
                                .valor(new BigDecimal(10))
                                .data(LocalDate.now().minusWeeks(7))
                                .tipo("RECEITA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(13L)
                                .descricao("Agua")
                                .valor(new BigDecimal(200))
                                .data(LocalDate.now().minusWeeks(10))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(14L)
                                .descricao("Luz")
                                .valor(new BigDecimal(900))
                                .data(LocalDate.now().minusWeeks(9))
                                .tipo("RECEITA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(15L)
                                .descricao("Netflix")
                                .valor(new BigDecimal(200))
                                .data(LocalDate.now().minusWeeks(12))
                                .tipo("DESPESA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(16L)
                                .descricao("Aluguel")
                                .valor(new BigDecimal(200))
                                .data(LocalDate.now().minusWeeks(14))
                                .tipo("RECEITA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build())

        );
    }
    
}
