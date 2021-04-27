package com.indytek.pufsmanagement.servicei;

import com.indytek.pufsmanagement.model.Pedido;

import java.util.Optional;

public interface DireccionServiceI {

	public void insertar (Pedido  pedido);
	public boolean borrar (int id);
	public boolean actualizar (Pedido pedido);
	public Optional<Pedido> buscarPorId (int id);
	
}
