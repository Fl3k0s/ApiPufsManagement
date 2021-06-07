package com.indytek.pufsmanagement.servicei;

import java.util.List;
import java.util.Optional;

import com.indytek.pufsmanagement.model.Pedido;
import com.indytek.pufsmanagement.model.TipoPedido;

/*
interfaz del servicio de pedidos

la clase PedidoService debe implementar esta interfaz y escribir sus metodos.
 */
public interface PedidoServiceI {

	public void insertar (Pedido  pedido);
	public boolean borrar (int id);
	public boolean actualizar (Pedido pedido);
	public Optional<Pedido> buscarPorId (int id);
	public List<Pedido> buscarTodos();
	public List<Pedido> buscarTodosHoy();
	public Pedido buscarUltimoAndroidHoy();
	public List<Pedido> buscarPorTipo(TipoPedido tipo);

	public void borrarNulos();

	public float gastoTotalHoy();
	public float gastoGestionHoy();
	public float beneficioTotalHoy();
	public float beneficioRealHoy();
	
}
