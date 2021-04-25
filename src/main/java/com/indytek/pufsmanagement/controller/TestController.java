package com.indytek.pufsmanagement.controller;

import com.indytek.pufsmanagement.model.*;
import com.indytek.pufsmanagement.servicei.*;
import com.indytek.pufsmanagement.util.Test;
import com.indytek.pufsmanagement.util.TestServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("pufs/test")
public class TestController {
	
	@Autowired PedidoServiceI servicioPedido;
	@Autowired ProductoServiceI servicioProducto;
	@Autowired UsuarioServiceI servicioUsuario;
	@Autowired PersonaServiceI servicioPersona;

	@Autowired TestServiceI servicioTest;
	
	
	@GetMapping("/installtest")
	public ResponseEntity<String> cargarDatos ()
	{
		ResponseEntity <String> response;

		Test test1 = Test.builder()
				.id(0)
				.name("test 1")
				.build();

		Test test2 = new Test(0,"test 2");

		System.out.println("objeto test1 fuera del servicio -> " + test1.toString() + " <-");

		servicioTest.insertar(test1);

		servicioTest.insertar(test2);


		//productos
		cargarComidas();

		cargarBebidas();

		//pedidos
		cargarPedidos();

		//personas
		cargarUsuarios();

		cargarEmpleados();

		cargarClientes();

	
		//necesario comprobar si este mensaje aparece para crear datos por defecto
		response =  new ResponseEntity<>("<h1>Carga realizada correctamente</h1>", HttpStatus.OK);
		
		return response;
	}

	public void cargarComidas()
	{
		Comida c1 = Comida.builder()
				.name("Estofado")
				.providerEmail("elpollolucas@yahoo.es")
				.kg(1)
				.build();servicioProducto.insertar(c1);

		Comida c2 = Comida.builder()
				.name("Pollo asado")
				.providerEmail("elpollolucas@yahoo.es")
				.kg(1.3f)
				.build();servicioProducto.insertar(c2);

		Comida c3 = Comida.builder()
				.name("Tortitas")
				.providerEmail("supercafe@gmail.com")
				.kg(0.5f)
				.build();servicioProducto.insertar(c3);

		Comida c4 = Comida.builder()
				.name("hamburguesa")
				.providerEmail("thegoodburger@tgb.es")
				.kg(1.2f)
				.build();servicioProducto.insertar(c4);

		Comida c5 = Comida.builder()
				.name("Perrito")
				.providerEmail("thegoodburger@tgb.es")
				.kg(0.8f)
				.build();servicioProducto.insertar(c5);
	}

	public void cargarBebidas()
	{
		Bebida b1 = Bebida.builder()
				.name("Coca-Cola")
				.providerEmail("productcenter@cocacola.com")
				.uds(20)
				.build();servicioProducto.insertar(b1);

		Bebida b2 = Bebida.builder()
				.name("Fanta")
				.providerEmail("productcenter@cocacola.com")
				.uds(10)
				.build();servicioProducto.insertar(b2);

		Bebida b3 = Bebida.builder()
				.name("Café")
				.providerEmail("supercafe@gmail.com")
				.uds(20)
				.build();servicioProducto.insertar(b3);

		Bebida b4 = Bebida.builder()
				.name("Zumo")
				.providerEmail("supercafe@gmail.com")
				.uds(20)
				.build();servicioProducto.insertar(b4);

		Bebida b5 = Bebida.builder()
				.name("Cerveza")
				.providerEmail("heineken@heineken.com")
				.uds(20)
				.build();servicioProducto.insertar(b5);
	}


	public void cargarPedidos()
	{
		Pedido p1 = Pedido.builder()
				.dateOrdered(LocalDate.of(2020,10,01))
				.dateReceived(LocalDate.of(2020,10,01))
				.active(false)
				.product(Comida.builder()
						.name("hamburguesa")
						.providerEmail("thegoodburger@tgb.es")
						.kg(1.2f)
						.build())
				.product(Bebida.builder()
						.name("Cerveza")
						.providerEmail("heineken@heineken.com")
						.uds(1)
						.build())
				.build();servicioPedido.insertar(p1);

		Pedido p2 = Pedido.builder()
				.dateOrdered(LocalDate.of(2020,11,11))
				.dateReceived(LocalDate.of(2020,11,12))
				.active(false)
				.product(Comida.builder()
						.name("Tortitas")
						.providerEmail("supercafe@gmail.com")
						.kg(0.5f)
						.build())
				.product(Bebida.builder()
						.name("Zumo")
						.providerEmail("supercafe@gmail.com")
						.uds(1)
						.build())
				.build();servicioPedido.insertar(p2);

		Pedido p3 = Pedido.builder()
				.dateOrdered(LocalDate.of(2020,11,23))
				.dateReceived(LocalDate.of(2020,11,23))
				.active(false)
				.product(Comida.builder()
						.name("Tortitas")
						.providerEmail("supercafe@gmail.com")
						.kg(0.5f)
						.build())
				.product(Bebida.builder()
						.name("Café")
						.providerEmail("supercafe@gmail.com")
						.uds(1)
						.build())
				.build();servicioPedido.insertar(p3);

		Pedido p4 = Pedido.builder()
				.dateOrdered(LocalDate.of(2020,11,23))
				.dateReceived(LocalDate.of(2020,11,23))
				.active(false)
				.product(Comida.builder()
						.name("Perrito")
						.providerEmail("thegoodburger@tgb.es")
						.kg(0.8f)
						.build())
				.product(Bebida.builder()
						.name("Fanta")
						.providerEmail("productcenter@cocacola.com")
						.uds(10)
						.build())
				.build();servicioPedido.insertar(p4);

		Pedido p5 = Pedido.builder()
				.dateOrdered(LocalDate.of(2020,12,8))
				.dateReceived(LocalDate.of(2020,12,8))
				.active(false)
				.product(Comida.builder()
						.name("Estofado")
						.providerEmail("elpollolucas@yahoo.es")
						.kg(1)
						.build())
				.product(Bebida.builder()
						.name("Coca-Cola")
						.providerEmail("productcenter@cocacola.com")
						.uds(20)
						.build())
				.build();servicioPedido.insertar(p5);
	}

	public void cargarUsuarios()
	{

		//cuenta admin
		Usuario u0 = Usuario.builder()
				.username("admin")
				.password("admin")
				.usertype(TipoUsuario.ADMIN)
				.build();servicioUsuario.insertar(u0);


		Usuario u1 = Usuario.builder()
				.username("aAguado69")
				.password("retractil")
				.usertype(TipoUsuario.EMPLEADO)
				.build();servicioUsuario.insertar(u1);

		Usuario u2 = Usuario.builder()
				.username("bolas")
				.password("megamega")
				.usertype(TipoUsuario.EMPLEADO)
				.build();servicioUsuario.insertar(u2);

		Usuario u3 = Usuario.builder()
				.username("RaquelGZ")
				.password("1234")
				.usertype(TipoUsuario.EMPLEADO)
				.build();servicioUsuario.insertar(u3);

		Usuario u4 = Usuario.builder()
				.username("mikkelcarballo")
				.password("ratoncitoperez43")
				.usertype(TipoUsuario.EMPLEADO)
				.build();servicioUsuario.insertar(u4);

		Usuario u5 = Usuario.builder()
				.username("chanaLopez")
				.password("firulaisperrito")
				.usertype(TipoUsuario.EMPLEADO)
				.build();servicioUsuario.insertar(u5);

		Usuario u6 = Usuario.builder()
				.username("ferputo")
				.password("milumilu123")
				.usertype(TipoUsuario.CLIENTE)
				.order(servicioPedido.buscarPorId(1).get())
				.build();servicioUsuario.insertar(u6);

		Usuario u7 = Usuario.builder()
				.username("sara_salami")
				.password("qwerty")
				.usertype(TipoUsuario.CLIENTE)
				.order(servicioPedido.buscarPorId(2).get())
				.build();servicioUsuario.insertar(u7);

		Usuario u8 = Usuario.builder()
				.username("pablosalasps")
				.password("matematico0101")
				.usertype(TipoUsuario.CLIENTE)
				.order(servicioPedido.buscarPorId(3).get())
				.build();servicioUsuario.insertar(u8);

		Usuario u9 = Usuario.builder()
				.username("pepinomarino")
				.password("vivaeltecno")
				.usertype(TipoUsuario.CLIENTE)
				.order(servicioPedido.buscarPorId(4).get())
				.build();servicioUsuario.insertar(u9);

		//john tiene dos usuarios(uno de empleado y otro de cliente)
		Usuario u10a = Usuario.builder()
				.username("john_fred_1993")
				.password("postres?")
				.usertype(TipoUsuario.EMPLEADO)
				.build();servicioUsuario.insertar(u10a);

		Usuario u10b = Usuario.builder()
				.username("freddy_mercury_john")
				.password("postres?")
				.usertype(TipoUsuario.CLIENTE)
				.order(servicioPedido.buscarPorId(5).get())
				.build();servicioUsuario.insertar(u10b);
	}

	public void cargarEmpleados()
	{
		Empleado e1 = Empleado.builder()
				.name("Alejandro").secondName1("Aguado").secondName2("Gutierrez")
				.email("aaguado@yahoo.es")
				.address("calle petunia 4")
				.usuario(servicioUsuario.buscarPorUsername("aAguado69").get())
				.position(Cargo.ENCARGADO)
				.build();servicioPersona.insertar(e1);

		Empleado e2 = Empleado.builder()
				.name("Victor").secondName1("Bolaños").secondName2("Gallego")
				.email("victor.boga@hotmail.com")
				.address("calle jose isbert 10")
				.usuario(servicioUsuario.buscarPorUsername("bolas").get())
				.position(Cargo.CAMARERO)
				.build();servicioPersona.insertar(e2);

		Empleado e3 = Empleado.builder()
				.name("Raquel").secondName1("Mosquera").secondName2("López")
				.email("rqraquelita@yahoo.es")
				.address("calle moraleja 124")
				.usuario(servicioUsuario.buscarPorUsername("RaquelGZ").get())
				.position(Cargo.CAMARERO)
				.build();servicioPersona.insertar(e3);

		Empleado e4 = Empleado.builder()
				.name("Mikkel").secondName1("Carballo").secondName2("Puebla")
				.email("mikkelcarballo@gmail.com")
				.address("calle oudrid 14")
				.usuario(servicioUsuario.buscarPorUsername("mikkelcarballo").get())
				.position(Cargo.REPARTIDOR)
				.build();servicioPersona.insertar(e4);

		Empleado e5 = Empleado.builder()
				.name("Laura").secondName1("Chana").secondName2("López")
				.email("laurachanalopez@gmail.com")
				.address("calle oudrid 14")
				.usuario(servicioUsuario.buscarPorUsername("chanaLopez").get())
				.position(Cargo.REPARTIDOR)
				.build();servicioPersona.insertar(e5);

		Empleado e6 = Empleado.builder()
				.name("John").secondName1("Fred").secondName2("")
				.email("iwanttofreakfree@gmail.com")
				.address("calle anastasia 23")
				.usuario(servicioUsuario.buscarPorUsername("john_fred_1993").get())
				.position(Cargo.REPARTIDOR)
				.build();servicioPersona.insertar(e6);

	}

	public void cargarClientes()
	{
		Cliente c1 = Cliente.builder()
				.name("Fernando").secondName1("Sánchez").secondName2("Martinez")
				.email("fersan88@gmail.com")
				.address("calle los aluches 20")
				.usuario(servicioUsuario.buscarPorUsername("ferputo").get())
				//.order()
				.build();servicioPersona.insertar(c1);

		Cliente c2 = Cliente.builder()
				.name("Sara").secondName1("Guitierrez").secondName2("Pan")
				.email("saritatuloquita@gmail.com")
				.address("calle gran via de san marcos 36")
				.usuario(servicioUsuario.buscarPorUsername("pablosalasps").get())
				//.order()
				.build();servicioPersona.insertar(c2);

		Cliente c3 = Cliente.builder()
				.name("Pablo").secondName1("Salas").secondName2("Perez")
				.email("pablosalasperez@gmail.com")
				.address("calle ancha 4")
				.usuario(servicioUsuario.buscarPorUsername("pablosalasps").get())
				//.order()
				.build();servicioPersona.insertar(c3);

		Cliente c4 = Cliente.builder()
				.name("Jose Luis").secondName1("Rodriguez").secondName2("Zapatero")
				.email("joselrlr@gmail.com")
				.address("calle demonios 66")
				.usuario(servicioUsuario.buscarPorUsername("pepinomarino").get())
				.build();servicioPersona.insertar(c4);

		Cliente c5 = Cliente.builder()
				.name("Fernando").secondName1("Sánchez").secondName2("Martinez")
				.email("fersan88@gmail.com")
				.address("calle los aluches 20")
				.usuario(servicioUsuario.buscarPorUsername("john_fred_1993").get())
				.build();servicioPersona.insertar(c5);

		Cliente c6 = Cliente.builder()
				.name("John").secondName1("Fred").secondName2("")
				.email("iwanttofreakfree@gmail.com")
				.address("calle anastasia 23")
				.usuario(servicioUsuario.buscarPorUsername("freddy_mercury_john").get())
				.build();servicioPersona.insertar(c6);

	}
		
}
