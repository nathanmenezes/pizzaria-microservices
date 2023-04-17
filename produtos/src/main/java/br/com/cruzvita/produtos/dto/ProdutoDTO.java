package br.com.cruzvita.produtos.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProdutoDTO {

    @NotBlank(message = "Preencha o nome!")
    private String nome;

    @NotNull(message = "Preencha o valor!")
    private Double valor;
}
