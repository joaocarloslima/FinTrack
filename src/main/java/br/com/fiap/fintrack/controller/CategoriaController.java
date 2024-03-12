package br.com.fiap.fintrack.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.fintrack.model.Categoria;
import br.com.fiap.fintrack.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("categoria")
@Slf4j
public class CategoriaController {

    @Autowired // Injeção de Dependência
    CategoriaRepository repository;

    @GetMapping
    public List<Categoria> index(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Categoria create(@RequestBody Categoria categoria){ // injeta + binding
        log.info("cadastrando categoria {}", categoria );
        repository.save(categoria);
        return categoria;
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> show(@PathVariable Long id){
        log.info("buscando categoria com id {} ", id);

        var categoriaEncontrada = repository.findById(id);


        if (categoriaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(categoriaEncontrada.get());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        log.info("apagando categoria {}", id);

        var categoriaEncontrada = repository.findById(id);

        if (categoriaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();

        repository.delete(categoriaEncontrada.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Categoria> update(
        @PathVariable Long id,
        @RequestBody Categoria categoria
    ){
        log.info("atualizando categoria {} para {}", id, categoria);

        var categoriaEncontrada = repository.findById(id);

        if (categoriaEncontrada.isEmpty())
            return ResponseEntity.notFound().build();


        var categoriaNova = new Categoria();
        categoriaNova.setId(id);
        categoriaNova.setNome(categoria.getNome());
        categoriaNova.setIcone(categoria.getIcone());

        repository.save(categoriaNova);
        
        return ResponseEntity.ok(categoriaNova);
    }








    // private Optional<Categoria> getCategoriaById(Long id) {
    //     var categoriaEncontrada = repository
    //                                 .stream()
    //                                 .filter( c -> c.id().equals(id))
    //                                 .findFirst();
    //     return categoriaEncontrada;
    // }


    
}
