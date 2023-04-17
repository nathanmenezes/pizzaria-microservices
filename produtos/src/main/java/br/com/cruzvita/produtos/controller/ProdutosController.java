package br.com.cruzvita.produtos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cruzvita.produtos.dto.ListaProdutos;
import br.com.cruzvita.produtos.dto.ProdutoDTO;
import br.com.cruzvita.produtos.model.Produto;
import br.com.cruzvita.produtos.service.ProdutoService;

@RestController
public class ProdutosController {
    
    @Autowired
    ProdutoService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody @Valid ProdutoDTO produto){
        return service.cadastrar(produto);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Optional<Produto>> listarId(@PathVariable(value = "id") Long id){
        return service.listarId(id);
    }

    @PutMapping("/alterar")
    public ResponseEntity<String> alterar(@RequestBody Produto produto){
        return service.alterar(produto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable(value = "id") Long id){
        return service.deletar(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listar(){
        return service.listar();
    }

    @GetMapping("/listar/produtos")
    public ResponseEntity<List<Produto>> listarProdutosPedido(@RequestBody List<ListaProdutos> listaProdutos){
        return service.listarProdutosPedido(listaProdutos);
    }

    @GetMapping("/listar/total")
    public ResponseEntity<Double> totalPedido(@RequestParam("ids") List<Long> listaProdutos){
        return service.totalPedido(listaProdutos);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
