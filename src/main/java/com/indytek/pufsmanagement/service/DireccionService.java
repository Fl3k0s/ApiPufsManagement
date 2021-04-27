package com.indytek.pufsmanagement.service;

import com.indytek.pufsmanagement.model.Direccion;
import com.indytek.pufsmanagement.model.Pedido;
import com.indytek.pufsmanagement.repository.DireccionRepository;
import com.indytek.pufsmanagement.repository.PedidoRepository;
import com.indytek.pufsmanagement.servicei.DireccionServiceI;
import com.indytek.pufsmanagement.servicei.PedidoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
/*
servicio de direcciones

los metodos que son llamados desde el controlador de direcciones se encuentran aqui
 */
public class DireccionService implements DireccionServiceI {
	
	@Autowired private DireccionRepository direccionRepo;

	@Override
	public void insertar(Direccion direccion) {

		direccionRepo.save(direccion);
		
	}

	@Override
	public boolean borrar(int id) {
		boolean x  = false;
		
		if(buscarPorId(id).isPresent()) {
			direccionRepo.deleteById(id);
			x = true;
		}

		return x;
	}

	@Override
	public boolean actualizar(Direccion direccion) {
		

		return false;
	}

	@Override
	public Optional<Direccion> buscarPorId(int id) {
		

		return direccionRepo.findById(id);
	}

}
