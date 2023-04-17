package br.com.cruzvita.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cruzvita.produtos.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
