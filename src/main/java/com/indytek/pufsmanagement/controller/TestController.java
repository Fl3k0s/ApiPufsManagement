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
import java.util.HashSet;

@RestController
@RequestMapping("pufs/test")
/*
Controlador test. En esta clase se encontrarán los metodos rest que son llamados por las aplicaciones

En este controlador se encontrarán todos los metodos de pruebas generales.

 */
public class TestController {
	
	@Autowired PedidoServiceI servicioPedido;
	@Autowired ProductoServiceI servicioProducto;
	@Autowired DireccionServiceI servicioDireccion;
	@Autowired UsuarioServiceI servicioUsuario;
	@Autowired PersonaServiceI servicioPersona;

	@Autowired TestServiceI servicioTest;
	
	@GetMapping("/holaMundo")
	public ResponseEntity<String>holaMundo(){return new ResponseEntity<String>("hola mundo",HttpStatus.OK);
	}
	@GetMapping("/installtest")
	public ResponseEntity<String> cargarDatos ()
	{
		ResponseEntity <String> response;
/*
		Test test1 = Test.builder()
				.id(0)
				.name("test 1")
				.build();

		Test test2 = new Test(0,"test 2");

		System.out.println("objeto test1 fuera del servicio -> " + test1.toString() + " <-");

		servicioTest.insertar(test1);

		servicioTest.insertar(test2);
*/

		//productos
		cargarComidas();

		cargarBebidas();

		//pedidos
		cargarPedidos();

		//personas
		cargarDireccion();

		cargarUsuarios();

		cargarEmpleados();

		cargarClientes();


		response =  new ResponseEntity<>("<h1>Carga realizada correctamente</h1>", HttpStatus.OK);
		
		return response;
	}

	//Carga de prueba de las comidas (PRODUCTO)
	public void cargarComidas()
	{
		Comida c1 = Comida.builder()
				.name("Estofado")
				.urlProducto("")
				.pvp(8)
				.tipo(Tipo.PLATO)
				.rango(Rango.PLATINO)
				.kg(1)
				.build();servicioProducto.insertar(c1);

		Comida c2 = Comida.builder()
				.name("Pollo asado")
				.urlProducto("")
				.pvp(9)
				.tipo(Tipo.PLATO)
				.rango(Rango.ORO)
				.kg(1.3f)
				.build();servicioProducto.insertar(c2);

		Comida c3 = Comida.builder()
				.name("Tortitas")
				.urlProducto("")
				.pvp(5)
				.tipo(Tipo.ENTRANTE)
				.rango(Rango.BRONCE)
				.kg(0.5f)
				.build();servicioProducto.insertar(c3);

		Comida c4 = Comida.builder()
				.name("hamburguesa")
				.urlProducto("")
				.pvp(7)
				.tipo(Tipo.PLATO)
				.rango(Rango.PLATINO)
				.kg(1.2f)
				.build();servicioProducto.insertar(c4);

		Comida c5 = Comida.builder()
				.name("Perrito")
				.urlProducto("")
				.pvp(6)
				.tipo(Tipo.ENTRANTE)
				.rango(Rango.DIAMANTE)
				.kg(0.8f)
				.build();servicioProducto.insertar(c5);
	}

	//Carga de prueba de las bebidas (PRODUCTO)
	public void cargarBebidas()
	{
		Bebida b1 = Bebida.builder()
				.name("Coca-Cola")
				.urlProducto("")
				.pvp(8)
				.tipo(Tipo.BEBIDA)
				.rango(Rango.BRONCE)
				.uds(20)
				.build();servicioProducto.insertar(b1);

		Bebida b2 = Bebida.builder()
				.name("Fanta")
				.urlProducto("")
				.pvp(8)
				.tipo(Tipo.BEBIDA)
				.rango(Rango.BRONCE)
				.uds(10)
				.build();servicioProducto.insertar(b2);

		Bebida b3 = Bebida.builder()
				.name("Café")
				.urlProducto("")
				.pvp(8)
				.tipo(Tipo.BEBIDA)
				.rango(Rango.PLATA)
				.uds(20)
				.build();servicioProducto.insertar(b3);

		Bebida b4 = Bebida.builder()
				.name("Zumo")
				.urlProducto("")
				.pvp(8)
				.tipo(Tipo.BEBIDA)
				.rango(Rango.DIAMANTE)
				.uds(20)
				.build();servicioProducto.insertar(b4);

		Bebida b5 = Bebida.builder()
				.name("Cerveza")
				.urlProducto("")
				.pvp(8)
				.tipo(Tipo.BEBIDA)
				.rango(Rango.PLATINO)
				.uds(20)
				.build();servicioProducto.insertar(b5);
	}


	//Carga de prueba de los pedidos
	public void cargarPedidos()
	{
		Pedido p1 = Pedido.builder()
				.dateOrdered(LocalDate.of(2020,10,01))
				.dateReceived(LocalDate.of(2020,10,01))
				.active(false)
				//.product()
				//.product()
				.build();servicioPedido.insertar(p1);

		Pedido p2 = Pedido.builder()
				.dateOrdered(LocalDate.of(2020,11,11))
				.dateReceived(LocalDate.of(2020,11,12))
				.active(false)
				//.product()
				//.product()
				.build();servicioPedido.insertar(p2);

		Pedido p3 = Pedido.builder()
				.dateOrdered(LocalDate.of(2020,11,23))
				.dateReceived(LocalDate.of(2020,11,23))
				.active(false)
				//.product()
				//.product()
				.build();servicioPedido.insertar(p3);

		Pedido p4 = Pedido.builder()
				.dateOrdered(LocalDate.of(2020,11,23))
				.dateReceived(LocalDate.of(2020,11,23))
				.active(false)
				//.product()
				//.product()
				.build();servicioPedido.insertar(p4);

		Pedido p5 = Pedido.builder()
				.dateOrdered(LocalDate.of(2020,12,8))
				//sin fecha de recibido puesto que esta activo.dateReceived(LocalDate.of(2020,12,8))
				.active(true)
				//.product()
				//.product()
				.build();servicioPedido.insertar(p5);
	}

	//Carga de prueba de las direcciones
	public void cargarDireccion()
	{

		Direccion d1 = Direccion.builder()
				.calle("Tulipan")
				.numero("48")
				.build();servicioDireccion.insertar(d1);

		Direccion d2 = Direccion.builder()
				.calle("Petunia")
				.numero("4")
				.portal("2")
				.piso(6)
				.puerta("1")
				.build();servicioDireccion.insertar(d2);

		Direccion d3 = Direccion.builder()
				.calle("Jose Isbert")
				.numero("10")
				.portal("2")
				.piso(1)
				.puerta("A")
				.build();servicioDireccion.insertar(d3);

		Direccion d4 = Direccion.builder()
				.calle("Moraleja")
				.numero("124")
				.portal("10")
				.piso(2)
				.puerta("B")
				.build();servicioDireccion.insertar(d4);

		Direccion d5 = Direccion.builder()
				.calle("Oudrid")
				.numero("14")
				.piso(3)
				.puerta("IZQ")
				.build();servicioDireccion.insertar(d5);

		Direccion d6 = Direccion.builder()
				.calle("Los Aluches")
				.numero("20")
				.portal("1")
				.piso(4)
				.puerta("F")
				.build();servicioDireccion.insertar(d6);

		Direccion d7 = Direccion.builder()
				.calle("Ancha")
				.numero("65")
				.portal("3")
				.piso(1)
				.puerta("1")
				.build();servicioDireccion.insertar(d7);

		Direccion d8 = Direccion.builder()
				.calle("Gran Vía")
				.numero("82")
				.piso(5)
				.puerta("C")
				.build();servicioDireccion.insertar(d8);

		Direccion d9 = Direccion.builder()
				.calle("Demonios")
				.numero("66")
				.portal("6")
				.piso(6)
				.puerta("6")
				.build();servicioDireccion.insertar(d9);

		Direccion d10 = Direccion.builder()
				.calle("Anastasia")
				.numero("23")
				.portal("2")
				.piso(6)
				.build();servicioDireccion.insertar(d10);

	}

	//Carga de prueba de los usuarios
	public void cargarUsuarios()
	{

		//cuenta admin
		Usuario u0 = Usuario.builder()
				.username("admin")
				.password("admin")
				.direccion(servicioDireccion.buscarPorId(1).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.build();servicioUsuario.insertar(u0);


		Usuario u1 = Usuario.builder()
				.username("aAguado69")
				.password("retractil")
				.direccion(servicioDireccion.buscarPorId(2).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.build();servicioUsuario.insertar(u1);

		Usuario u2 = Usuario.builder()
				.username("bolas")
				.password("megamega")
				.direccion(servicioDireccion.buscarPorId(3).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.build();servicioUsuario.insertar(u2);

		Usuario u3 = Usuario.builder()
				.username("RaquelGZ")
				.password("1234")
				.direccion(servicioDireccion.buscarPorId(4).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.build();servicioUsuario.insertar(u3);

		Usuario u4 = Usuario.builder()
				.username("mikkelcarballo")
				.password("ratoncitoperez43")
				.direccion(servicioDireccion.buscarPorId(5).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.build();servicioUsuario.insertar(u4);

		Usuario u5 = Usuario.builder()
				.username("chanaLopez")
				.password("firulaisperrito")
				.direccion(servicioDireccion.buscarPorId(5).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.build();servicioUsuario.insertar(u5);

		Usuario u6 = Usuario.builder()
				.username("ferputo")
				.password("milumilu123")
				.direccion(servicioDireccion.buscarPorId(6).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.order(servicioPedido.buscarPorId(1).get())
				.build();servicioUsuario.insertar(u6);

		Usuario u7 = Usuario.builder()
				.username("sara_salami")
				.password("qwerty")
				.direccion(servicioDireccion.buscarPorId(7).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.order(servicioPedido.buscarPorId(2).get())
				.build();servicioUsuario.insertar(u7);

		Usuario u8 = Usuario.builder()
				.username("pablosalasps")
				.password("matematico0101")
				.direccion(servicioDireccion.buscarPorId(8).get())
				.rango(Rango.PLATA)
				.orders(new HashSet<>())
				.order(servicioPedido.buscarPorId(3).get())
				.build();servicioUsuario.insertar(u8);

		Usuario u9 = Usuario.builder()
				.username("pepinomarino")
				.password("vivaeltecno")
				.direccion(servicioDireccion.buscarPorId(9).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.order(servicioPedido.buscarPorId(4).get())
				.build();servicioUsuario.insertar(u9);

		//john tiene dos usuarios
		Usuario u10a = Usuario.builder()
				.username("john_fred_1993")
				.password("postres?")
				.direccion(servicioDireccion.buscarPorId(10).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.build();servicioUsuario.insertar(u10a);

		Usuario u10b = Usuario.builder()
				.username("john_fred")
				.password("postres?si!")
				.direccion(servicioDireccion.buscarPorId(10).get())
				.rango(Rango.ORO)
				.orders(new HashSet<>())
				.build();servicioUsuario.insertar(u10b);
	}

	//Carga de prueba de los empleados (PERSONA)
	public void cargarEmpleados()
	{
		Empleado e1 = Empleado.builder()
				.name("Alejandro").secondName1("Aguado").secondName2("Gutierrez")
				.email("aaguado@yahoo.es")
				.usuario(servicioUsuario.buscarPorUsername("aAguado69").get())
				.position(Cargo.ENCARGADO)
				.build();servicioPersona.insertar(e1);

		Empleado e2 = Empleado.builder()
				.name("Victor").secondName1("Bolaños").secondName2("Gallego")
				.email("victor.boga@hotmail.com")
				.usuario(servicioUsuario.buscarPorUsername("bolas").get())
				.position(Cargo.CAMARERO)
				.build();servicioPersona.insertar(e2);

		Empleado e3 = Empleado.builder()
				.name("Raquel").secondName1("Mosquera").secondName2("López")
				.email("rqraquelita@yahoo.es")
				.usuario(servicioUsuario.buscarPorUsername("RaquelGZ").get())
				.position(Cargo.CAMARERO)
				.build();servicioPersona.insertar(e3);

		Empleado e4 = Empleado.builder()
				.name("Mikkel").secondName1("Carballo").secondName2("Puebla")
				.email("mikkelcarballo@gmail.com")
				.usuario(servicioUsuario.buscarPorUsername("mikkelcarballo").get())
				.position(Cargo.REPARTIDOR)
				.build();servicioPersona.insertar(e4);

		Empleado e5 = Empleado.builder()
				.name("Laura").secondName1("Chana").secondName2("López")
				.email("laurachanalopez@gmail.com")
				.usuario(servicioUsuario.buscarPorUsername("chanaLopez").get())
				.position(Cargo.REPARTIDOR)
				.build();servicioPersona.insertar(e5);

	}

	//Carga de prueba de los clientes (PERSONA)
	public void cargarClientes()
	{
		Cliente c1 = Cliente.builder()
				.name("Fernando").secondName1("Sánchez").secondName2("Martinez")
				.email("fersan88@gmail.com")
				.usuario(servicioUsuario.buscarPorUsername("ferputo").get())
				//.order()
				.build();servicioPersona.insertar(c1);

		Cliente c2 = Cliente.builder()
				.name("Sara").secondName1("Guitierrez").secondName2("Pan")
				.email("saritatuloquita@gmail.com")
				.usuario(servicioUsuario.buscarPorUsername("pablosalasps").get())
				//.order()
				.build();servicioPersona.insertar(c2);

		Cliente c3 = Cliente.builder()
				.name("Pablo").secondName1("Salas").secondName2("Perez")
				.email("pablosalasperez@gmail.com")
				.usuario(servicioUsuario.buscarPorUsername("pablosalasps").get())
				//.order()
				.build();servicioPersona.insertar(c3);

		Cliente c4 = Cliente.builder()
				.name("Jose Luis").secondName1("Rodriguez").secondName2("Zapatero")
				.email("joselrlr@gmail.com")
				.usuario(servicioUsuario.buscarPorUsername("pepinomarino").get())
				.build();servicioPersona.insertar(c4);

		Cliente c5 = Cliente.builder()
				.name("John").secondName1("Fred").secondName2("")
				.email("iwanttofreakfree@gmail.com")
				.usuario(servicioUsuario.buscarPorUsername("john_fred_1993").get())
				.usuario(servicioUsuario.buscarPorUsername("john_fred").get())
				.build();servicioPersona.insertar(c5);

	}
		
}
