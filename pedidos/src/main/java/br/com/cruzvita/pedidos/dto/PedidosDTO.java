package br.com.cruzvita.pedidos.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PedidosDTO {
    
    @NotBlank(message = "Preencha o ID do cliente!")
    private Long idCliente;

    @NotBlank(message = "Preencha a lista de produtos!")
    private List<Long> idProdutos;

}
