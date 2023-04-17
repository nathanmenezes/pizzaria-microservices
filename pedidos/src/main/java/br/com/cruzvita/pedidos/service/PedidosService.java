package br.com.cruzvita.pedidos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.cruzvita.pedidos.dto.PedidosDTO;
import br.com.cruzvita.pedidos.model.PedidoStatus;
import br.com.cruzvita.pedidos.model.Pedidos;
import br.com.cruzvita.pedidos.repository.PedidosRepository;

@Service
public class PedidosService {

    @Autowired
    PedidosRepository repository;

    public ResponseEntity<String> gerarPedido(PedidosDTO pedido) {
        RestTemplate template = new RestTemplate();

        String resourceUrl = "http://localhost:8081/existe/" + pedido.getIdCliente();

        ResponseEntity<Boolean> response = template.getForEntity(resourceUrl, Boolean.class);

        if(!response.getBody()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe no sistema!");
        }

        Pedidos pedidoModel = new Pedidos();

        BeanUtils.copyProperties(pedido, pedidoModel);

        String listaProdutos = String.join(",", pedido.getIdProdutos().stream().map(Object::toString).collect(Collectors.toList()));

        String produtosURL = "http://localhost:8085/listar/total?ids="+listaProdutos;

        ResponseEntity<Double> retorno = template.getForEntity(produtosURL, Double.class);

        if(retorno.getStatusCode() == HttpStatus.OK){
            pedidoModel.setTotal(retorno.getBody());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao calcular o total");
        }

        pedidoModel.setStatus(PedidoStatus.PENDENTE);
        repository.save(pedidoModel);

        return ResponseEntity.status(HttpStatus.OK).body("Pedido Gerado Com Sucesso!");
    }

    public ResponseEntity<List<Pedidos>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    public ResponseEntity<Pedidos> listarId(Long id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id).get());
    }

    public ResponseEntity<String> deletar(Long id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado no sistema!");
        }

        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido Deletado com sucesso!");
    }

    public ResponseEntity<String> concluir(Long id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado no sistema!");
        }

        Pedidos pedido = repository.findById(id).get();
        pedido.setStatus(PedidoStatus.CONCLUIDO);
        repository.save(pedido);
        return ResponseEntity.status(HttpStatus.OK).body("Pedido Concluido com Sucesso!");
    }

}
