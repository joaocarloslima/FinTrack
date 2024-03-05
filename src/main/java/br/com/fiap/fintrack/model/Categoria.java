package br.com.fiap.fintrack.model;

import java.util.Random;

//imutável
public record Categoria(Long id, String nome, String icone) {

    public Categoria(Long id, String nome, String icone){
        this.id = Math.abs( new Random().nextLong() );
        this.nome = nome;
        this.icone = icone;
    }

}
