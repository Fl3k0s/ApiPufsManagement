package com.indytek.pufsmanagement.servicei;

import java.util.List;
import java.util.Optional;

import com.indytek.pufsmanagement.model.Pedido;
/*
interfaz del servicio de pedidos

la clase PedidoService debe implementar esta interfaz y escribir sus metodos.
 */
public interface PedidoServiceI {

	public void insertar (Pedido  pedido);
	public boolean borrar (int id);
	public boolean actualizar (Pedido pedido);
	public Optional<Pedido> buscarPorId (int id);
	public List<Pedido> buscarPorActivo (boolean active);
	public List<Pedido> buscarTodos();
	public void borrarNulos();
	
}
