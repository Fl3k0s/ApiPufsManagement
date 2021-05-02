package com.indytek.pufsmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indytek.pufsmanagement.model.Pedido;
import com.indytek.pufsmanagement.repository.PedidoRepository;
import com.indytek.pufsmanagement.servicei.PedidoServiceI;

@Service
/*
servicio de pedidos

los metodos que son llamados desde el controlador de pedidos se encuentran aqui
 */
public class PedidoService implements PedidoServiceI{
	
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

	@Override
	public List<Pedido> buscarPorActivo(boolean active) {


		return pedidoRepo.findByActive(active);
	}

}
