package com.indytek.pufsmanagement.repository;

import java.util.List;
import java.util.Optional;

import com.indytek.pufsmanagement.model.TipoPedido;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.indytek.pufsmanagement.model.Pedido;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
repositorio de pedidos
 */
@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Integer> {

	Optional<Pedido> findById (int id);
	List<Pedido> findByTipo (TipoPedido tipo);
	List<Pedido> findByAbierto (boolean abierto);

	@Transactional
	@Modifying
	@Query("DELETE FROM Pedido p WHERE p.dateOrdered IS NULL")
	void deleteNull();
	
}
