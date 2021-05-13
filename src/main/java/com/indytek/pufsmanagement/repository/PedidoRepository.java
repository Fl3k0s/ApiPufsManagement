package com.indytek.pufsmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.indytek.pufsmanagement.model.Pedido;
import org.springframework.stereotype.Repository;

/*
repositorio de pedidos
 */
@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

	Optional<Pedido> findById (int id);
	List<Pedido> findByActive (boolean active);
	
}
