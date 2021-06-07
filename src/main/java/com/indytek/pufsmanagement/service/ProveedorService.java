package com.indytek.pufsmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indytek.pufsmanagement.model.Proveedor;
import com.indytek.pufsmanagement.repository.ProveedorRepository;
import com.indytek.pufsmanagement.servicei.ProveedorServiceI;
@Service

/*
servicio de proveedores

los metodos que son llamados desde el controlador de proveedores se encuentran aqui
 */
public class ProveedorService implements ProveedorServiceI{
	@Autowired ProveedorRepository proveedorRepo;

	@Override
	public Optional<Proveedor> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return proveedorRepo.findById(id);
	}

	@Override
	public Optional<Proveedor> buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return proveedorRepo.findByNombre(nombre);
	}

	@Override
	public List<Proveedor> buscarTodos() {
		// TODO Auto-generated method stub
		return proveedorRepo.findAll();
	}

	@Override
	public void insertar(Proveedor p) {
		// TODO Auto-generated method stub
		proveedorRepo.save(p);
	}

}
