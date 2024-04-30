package br.com.fiap.fintrack.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.fintrack.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    // @Query("SELECT m FROM Movimentacao m WHERE m.categoria.nome = :categoriaNome ORDER BY data DESC LIMIT 1 GROUP BY categoria ") // JPQL
    Page<Movimentacao> findByCategoriaNomeIgnoreCase(String categoriaNome, Pageable pageable);

    @Query("SELECT m FROM Movimentacao m WHERE m.categoria.nome = :categoriaNome AND MONTH(m.data) = :mes")
    Page<Movimentacao> findByCategoriaNomeIgnoreCaseAndDataMonth(String categoriaNome, int mes, Pageable pageable);

    @Query("SELECT m FROM Movimentacao m WHERE MONTH(m.data) = :mes")
    Page<Movimentacao> findByDataMonth(Integer mes, Pageable pageable);
    
}
