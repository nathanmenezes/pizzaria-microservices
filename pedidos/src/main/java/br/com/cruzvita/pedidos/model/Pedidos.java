package br.com.cruzvita.pedidos.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PEDIDOS_TB")
@Data
public class Pedidos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idCliente;

    @ElementCollection
    @CollectionTable(name = "PEDIDOS_PRODUTOS_TB")
    private List<Long> idProdutos;

    private PedidoStatus status;

    private Double total = 0.0;
}
