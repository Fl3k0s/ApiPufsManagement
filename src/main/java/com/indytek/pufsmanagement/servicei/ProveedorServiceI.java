package com.indytek.pufsmanagement.servicei;

import java.util.List;
import java.util.Optional;

import com.indytek.pufsmanagement.model.Proveedor;

public interface ProveedorServiceI {
	public Optional<Proveedor> buscarPorId(int id);
	public Optional<Proveedor> buscarPorNombre(String nombre);
	List<Proveedor> buscarTodos();
	void insertar(Proveedor p);

}
