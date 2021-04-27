package com.indytek.pufsmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.repository.ProductoRepository;
import com.indytek.pufsmanagement.servicei.ProductoServiceI;

@Service
/*
servicio de productos

los metodos que son llamados desde el controlador de productos se encuentran aqui
 */
public class ProductoService implements ProductoServiceI{

	@Autowired private ProductoRepository productoRepo;

	@Override
	public void insertar(Producto producto) {

		productoRepo.save(producto);
		
	}

	@Override
	public boolean borrar(int id) {
		boolean x  = false;
		
		if(buscarPorId(id).isPresent()) {
			productoRepo.deleteById(id);
			x = true;
		}

		return x;
	}

	@Override
	public boolean actualizar(Producto producto) {


		return false;
	}

	@Override
	public Optional<Producto> buscarPorId(int id) {
		

		return productoRepo.findById(id);
	}

	@Override
	public List<Producto> buscarTodos() {
		return productoRepo.findAll();
	}


}
