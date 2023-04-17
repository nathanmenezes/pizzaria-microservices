package br.com.cruzvita.produtos.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cruzvita.produtos.dto.ListaProdutos;
import br.com.cruzvita.produtos.dto.ProdutoDTO;
import br.com.cruzvita.produtos.model.Produto;
import br.com.cruzvita.produtos.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    ProdutoRepository repository;

    public ResponseEntity<String> cadastrar(ProdutoDTO produto) {
        Produto produtoModel = new Produto();
        BeanUtils.copyProperties(produto, produtoModel);
        repository.save(produtoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Produto salvo com sucesso!");
    }

    public ResponseEntity<Optional<Produto>> listarId(Long id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
    }

    public ResponseEntity<String> alterar(Produto produto) {
        if(!repository.existsById(produto.getId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto não encontrado no sistema!");
        }

        repository.save(produto);
        return ResponseEntity.status(HttpStatus.OK).body("Produto alterado com sucesso!");
    }

    public ResponseEntity<String> deletar(Long id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não Encontado!");
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto Deletado com Sucesso!");
    }

    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    public ResponseEntity<List<Produto>> listarProdutosPedido(List<ListaProdutos> listaProdutos) {
        List<Produto> listaTeste = new ArrayList<>();

        for (ListaProdutos produtoId : listaProdutos) {
            listaTeste.add(repository.findById(produtoId.getIdProduto()).get());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(listaTeste);
    }

    public ResponseEntity<Double> totalPedido(List<Long> listaProdutos) {
        double total = 0;

        for (Long produtoId : listaProdutos) {
            if(repository.findById(produtoId).isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            total += repository.findById(produtoId).get().getValor();
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }
}
