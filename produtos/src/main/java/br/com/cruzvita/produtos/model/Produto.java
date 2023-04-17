package br.com.cruzvita.produtos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PRODUTO_TB")
@Data
public class Produto {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private Double valor;

}
