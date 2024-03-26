package br.com.fiap.fintrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.fintrack.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    
}
