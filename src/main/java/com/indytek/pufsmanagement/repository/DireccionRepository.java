package com.indytek.pufsmanagement.repository;

import com.indytek.pufsmanagement.model.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DireccionRepository extends CrudRepository<Pedido, Integer> {

	Optional<Pedido> findById (int id);
	
}
