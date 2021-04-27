package com.indytek.pufsmanagement.servicei;

import com.indytek.pufsmanagement.model.Direccion;
import com.indytek.pufsmanagement.model.Pedido;

import java.util.Optional;
/*
interfaz del servicio de direcciones

la clase DireccionService debe implementar esta interfaz y escribir sus metodos.
 */
public interface DireccionServiceI {

	public void insertar (Direccion direccion);
	public boolean borrar (int id);
	public boolean actualizar (Direccion direccion);
	public Optional<Direccion> buscarPorId (int id);
	
}
