package com.indytek.pufsmanagement.service;

import com.indytek.pufsmanagement.model.Pedido;
import com.indytek.pufsmanagement.repository.PedidoRepository;
import com.indytek.pufsmanagement.servicei.PedidoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DireccionService implements PedidoServiceI{
	
	@Autowired private PedidoRepository pedidoRepo;

	@Override
	public void insertar(Pedido pedido) {
		
		pedidoRepo.save(pedido);
		
	}

	@Override
	public boolean borrar(int id) {
		boolean x  = false;
		
		if(buscarPorId(id).isPresent()) {
			pedidoRepo.deleteById(id);
			x = true;
		}

		return x;
	}

	@Override
	public boolean actualizar(Pedido pedido) {
		

		return false;
	}

	@Override
	public Optional<Pedido> buscarPorId(int id) {
		

		return pedidoRepo.findById(id);
	}

}
