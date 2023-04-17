package br.com.cruzvita.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cruzvita.pedidos.model.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos, Long>{

}
