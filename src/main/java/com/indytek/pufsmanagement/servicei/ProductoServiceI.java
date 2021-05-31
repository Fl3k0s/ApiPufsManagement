package com.indytek.pufsmanagement.servicei;

import java.util.List;
import java.util.Optional;

import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Rango;
import com.indytek.pufsmanagement.model.Tipo;

/*
interfaz del servicio de productos

la clase ProductoService debe implementar esta interfaz y escribir sus metodos.
 */
public interface ProductoServiceI {

	public void insertar (Producto  producto);
	public boolean borrar (int id);
	public boolean actualizar (Producto producto);
	public Optional<Producto> buscarPorId (int id);
	public List<Producto> buscarTodos ();
	public List<Producto> buscarPorRango (Rango rango);
	public List<Producto> buscarPorTipo (Tipo tipo);
	
}
