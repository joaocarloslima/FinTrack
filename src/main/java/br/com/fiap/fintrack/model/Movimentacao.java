package br.com.fiap.fintrack.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.fiap.fintrack.controller.MovimentacaoController;
import br.com.fiap.fintrack.validation.TipoMovimentacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao extends EntityModel<Movimentacao> {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "{movimentacao.descricao.notblank}")
    @Size(min = 3, max = 255, message = "{movimentacao.descricao.size}")
    private String descricao;
    
    @Positive(message = "{movimentacao.valor.positive}")
    private BigDecimal valor;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @TipoMovimentacao
    private String tipo; //RECEITA | DESPESA

    @ManyToOne
    private Categoria categoria;

    public EntityModel<Movimentacao> toEntityModel() {
        return EntityModel.of(
            this,
            linkTo(methodOn(MovimentacaoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(MovimentacaoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(MovimentacaoController.class).index(null, null, null)).withRel("contents")
        );
    }

}
