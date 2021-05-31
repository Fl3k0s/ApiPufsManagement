package com.indytek.pufsmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.indytek.pufsmanagement.model.Rango;
import com.indytek.pufsmanagement.model.Tipo;
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

	@Override
	public List<Producto> buscarPorRango(Rango rango) {

		List<Producto> products = new ArrayList<Producto>();

		if(rango == Rango.DIAMANTE){
			products.addAll(productoRepo.findByRango(Rango.DIAMANTE));
			products.addAll(productoRepo.findByRango(Rango.PLATINO));
			products.addAll(productoRepo.findByRango(Rango.ORO));
			products.addAll(productoRepo.findByRango(Rango.PLATA));
			products.addAll(productoRepo.findByRango(Rango.BRONCE));
		}else if(rango == Rango.PLATINO){
			products.addAll(productoRepo.findByRango(Rango.PLATINO));
			products.addAll(productoRepo.findByRango(Rango.ORO));
			products.addAll(productoRepo.findByRango(Rango.PLATA));
			products.addAll(productoRepo.findByRango(Rango.BRONCE));
		}else if(rango == Rango.ORO){
			products.addAll(productoRepo.findByRango(Rango.ORO));
			products.addAll(productoRepo.findByRango(Rango.PLATA));
			products.addAll(productoRepo.findByRango(Rango.BRONCE));
		}else if(rango == Rango.PLATA){
			products.addAll(productoRepo.findByRango(Rango.PLATA));
			products.addAll(productoRepo.findByRango(Rango.BRONCE));
		}else if(rango == Rango.BRONCE){
			products.addAll(productoRepo.findByRango(Rango.BRONCE));
		}

		return products;
	}

	@Override
	public List<Producto> buscarPorTipo(Tipo tipo) {

		return productoRepo.findByTipo(tipo);
	}


}
