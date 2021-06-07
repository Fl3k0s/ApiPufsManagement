package com.indytek.pufsmanagement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Tipo;
import com.indytek.pufsmanagement.model.TipoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indytek.pufsmanagement.model.Pedido;
import com.indytek.pufsmanagement.repository.PedidoRepository;
import com.indytek.pufsmanagement.servicei.PedidoServiceI;

@Service
/*
servicio de pedidos

los metodos que son llamados desde el controlador de pedidos se encuentran aqui
 */
public class PedidoService implements PedidoServiceI{
	
	@Autowired private PedidoRepository pedidoRepo;

	@Override
	public void insertar(Pedido pedido) {
		
		pedidoRepo.save(pedido);
		
	}

	@Override
	public boolean borrar(int id) {
		boolean x  = false;
		
		if(buscarPorId(id).isPresent()) {
			pedidoRepo.deleteById(id);
			x = true;
		}

		return x;
	}

	@Override
	public boolean actualizar(Pedido pedido) {
		
		pedidoRepo.save(pedido);
		return true;
	}

	@Override
	public Optional<Pedido> buscarPorId(int id) {
		

		return pedidoRepo.findById(id);
	}

	@Override
	public List<Pedido> buscarTodos() {
		return (List<Pedido>)pedidoRepo.findAll();
	}

	@Override
	public List<Pedido> buscarTodosHoy() {

		Iterable<Pedido> itr = pedidoRepo.findAll();

		List<Pedido> lista = new ArrayList<>();

		for(Pedido p : itr){
			//se recogen todos los pedidos desde las 2 hasta las 2 del dia siguiente.
			if(p.getDateOrdered().isAfter(LocalDateTime.of(LocalDate.now(), LocalTime.of(2,0,0)))
				&& p.getDateOrdered().isBefore(LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(2,0,0)))) {
					lista.add(p);
					System.out.println("PEDIDO PASÓ EL FILTRO DEL DIA : " + p.toString());
			}
		}

		return lista;
	}

	@Override
	public Pedido buscarUltimoAndroidHoy() {

		Pedido pedido = buscarTodosHoy().stream()
				.filter(p -> p.isAndroid())
				.max((p1, p2) -> p1.getDateOrdered().compareTo(p2.getDateOrdered()))
				.orElse(Pedido.builder().id(0).build());

		return pedido;
	}

	@Override
	public void borrarNulos() {
		pedidoRepo.deleteNull();
	}

	//los siguientes metodos recogen los pedidos y sus gastos solo del mismo dia

	@Override
	public float gastoGestionHoy() {

		float gasto = 0;

		gasto = 40;

		return gasto;
	}

	@Override
	public float gastoTotalHoy() {

		List<Pedido> pedidos = buscarTodosHoy();

		System.out.println("PEDIDO N1 EN GASTO TOTAL HOY:" + pedidos);

		float gasto = 0;

		for(Pedido p : pedidos)
			for(Producto pr : p.getProducts()){

				gasto += pr.getPc();
				System.out.println("PRECIO DE COMPRA DEL PRODUCTO (PedidoService) -> " + pr.getPc());
			}

		return gasto;
	}

	@Override
	public float beneficioTotalHoy() {

		List<Pedido> pedidos = buscarTodosHoy();

		float beneficio = 0;

		for(Pedido p : pedidos)
			for(Producto pr : p.getProducts())
				beneficio += pr.getPvp();

		return beneficio;
	}

	@Override
	public float beneficioRealHoy() {

		float beneficio = 0;

		beneficio += (beneficioTotalHoy() - (gastoTotalHoy() + gastoGestionHoy()));

		return beneficio;
	}

	@Override
	public List<Pedido> buscarPorTipo(TipoPedido tipo) {

		return pedidoRepo.findByTipo(tipo);
	}


}
