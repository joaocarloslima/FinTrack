package br.com.fiap.fintrack.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.fintrack.model.Movimentacao;
import br.com.fiap.fintrack.repository.MovimentacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("movimentacao")
@Slf4j
public class MovimentacaoController {

    @Autowired
    MovimentacaoRepository repository;

    @Autowired
    PagedResourcesAssembler<Movimentacao> pageAssembler;

    @GetMapping("{id}")
    public EntityModel<Movimentacao> show(@PathVariable Long id){
        var movimentacao = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("movimentação não encontrada")
        );

        return movimentacao.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Movimentacao> destroy(@PathVariable Long id){
        repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("movimentação não encontrada")
        );

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public PagedModel<EntityModel<Movimentacao>> index(
        @ParameterObject @PageableDefault(size = 5, sort = "data", direction = Direction.DESC) Pageable pageable,
        @RequestParam(required = false) String categoriaNome,
        @RequestParam(required = false) Integer mes

    ){
        Page<Movimentacao> page = null;

        if (categoriaNome != null && mes != null){
            page = repository.findByCategoriaNomeIgnoreCaseAndDataMonth(categoriaNome, mes, pageable);
        }

        if(mes != null){
            page = repository.findByDataMonth(mes, pageable);
        }

        if (categoriaNome != null){
            page = repository.findByCategoriaNomeIgnoreCase(categoriaNome, pageable);
        }

        if(page == null){
            page = repository.findAll(pageable);
        }

        return pageAssembler.toModel(page);

    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<EntityModel<Movimentacao>> create(@RequestBody @Valid Movimentacao movimentacao){
        repository.save(movimentacao);

        return ResponseEntity
                    .created(movimentacao.toEntityModel().getRequiredLink("self").toUri())
                    .body(movimentacao.toEntityModel());
    }


}
