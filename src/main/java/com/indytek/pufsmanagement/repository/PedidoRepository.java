package com.indytek.pufsmanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.indytek.pufsmanagement.model.Pedido;
/*
repositorio de pedidos
 */
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

	Optional<Pedido> findById (int id);
	
}
