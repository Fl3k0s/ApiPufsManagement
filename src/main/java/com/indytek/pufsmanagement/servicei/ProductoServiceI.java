package com.indytek.pufsmanagement.servicei;

import java.util.Optional;

import com.indytek.pufsmanagement.model.Producto;

public interface ProductoServiceI {

	public void insertar (Producto  producto);
	public boolean borrar (int id);
	public boolean actualizar (Producto producto);
	public Optional<Producto> buscarPorId (int id);
	
}
