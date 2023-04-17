package br.com.cruzvita.pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cruzvita.pedidos.dto.PedidosDTO;
import br.com.cruzvita.pedidos.model.Pedidos;
import br.com.cruzvita.pedidos.service.PedidosService;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    PedidosService service;

    @PostMapping("/gerar")
    public ResponseEntity<String> gerarPedido(@RequestBody PedidosDTO pedido){
        return service.gerarPedido(pedido);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Pedidos> listarId(@PathVariable(value = "id") Long id){
        return service.listarId(id);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable(value = "id") Long id){
        return service.deletar(id);
    }

    @PutMapping("/concluir/{id}")
    public ResponseEntity<String> concluir(@PathVariable(value = "id") Long id){
        return service.concluir(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pedidos>> listar(){
        return service.listar();
    }
}
