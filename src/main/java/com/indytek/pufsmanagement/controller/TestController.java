package com.indytek.pufsmanagement.controller;

import com.indytek.pufsmanagement.model.*;
import com.indytek.pufsmanagement.servicei.*;
import com.indytek.pufsmanagement.util.TestServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

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
	@Autowired ProveedorServiceI servicioProveedor;

	@Autowired TestServiceI servicioTest;

	//prueba básica
	@GetMapping("/holaMundo")
	public ResponseEntity<String>holaMundo(){
		return new ResponseEntity<String>("hola mundo",HttpStatus.OK);
	}

	//método de carga de datos completo
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
		try {

			//productos
			cargarComidas();

			cargarBebidas();

			//pedidos
			cargarPedidos();

			//direcciones
			cargarDireccion();

			//personas
			cargarEmpleados();

			cargarClientes();

			//usuarios
			cargarUsuarios();

			//proveedores
			cargarProveedores();


			response = new ResponseEntity<>("<h1>Carga realizada correctamente</h1>", HttpStatus.OK);

		}catch(Exception e){

			e.printStackTrace();
			response = new ResponseEntity<>("<h1>Carga no realizada</h1>", HttpStatus.NOT_FOUND);

		}

		return response;
	}

	//Carga de prueba de las comidas (PRODUCTO)
	public void cargarComidas()
	{
		Comida c1 = Comida.builder()
				.name("Estofado")
				.urlProducto("")
				.pc(5)
				.pvp(8)
				.stock(10)
				.tipo(Tipo.PLATO)
				.rango(Rango.PLATINO)
				.kg(1)
				.build();servicioProducto.insertar(c1);

		Comida c2 = Comida.builder()
				.name("Pollo asado")
				.urlProducto("")
				.pc(6)
				.pvp(9)
				.stock(10)
				.tipo(Tipo.PLATO)
				.rango(Rango.ORO)
				.kg(1.3f)
				.build();servicioProducto.insertar(c2);

		Comida c3 = Comida.builder()
				.name("Tortitas")
				.urlProducto("")
				.pc(2)
				.pvp(5)
				.stock(10)
				.tipo(Tipo.ENTRANTE)
				.rango(Rango.BRONCE)
				.kg(0.5f)
				.build();servicioProducto.insertar(c3);

		Comida c4 = Comida.builder()
				.name("hamburguesa")
				.urlProducto("")
				.pc(4)
				.pvp(7)
				.stock(10)
				.tipo(Tipo.PLATO)
				.rango(Rango.PLATINO)
				.kg(1.2f)
				.build();servicioProducto.insertar(c4);

		Comida c5 = Comida.builder()
				.name("Perrito")
				.urlProducto("")
				.pc(4)
				.pvp(6)
				.stock(10)
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
				.pc(1)
				.pvp(2)
				.stock(10)
				.tipo(Tipo.BEBIDA)
				.rango(Rango.BRONCE)
				.volumen(0.33f)
				.build();servicioProducto.insertar(b1);

		Bebida b2 = Bebida.builder()
				.name("Fanta")
				.urlProducto("")
				.pc(1)
				.pvp(2)
				.stock(10)
				.tipo(Tipo.BEBIDA)
				.rango(Rango.BRONCE)
				.volumen(0.33f)
				.build();servicioProducto.insertar(b2);

		Bebida b3 = Bebida.builder()
				.name("Café")
				.urlProducto("")
				.pc(0.7f)
				.pvp(1.4f)
				.stock(10)
				.tipo(Tipo.BEBIDA)
				.rango(Rango.PLATA)
				.volumen(0.3f)
				.build();servicioProducto.insertar(b3);

		Bebida b4 = Bebida.builder()
				.name("Zumo")
				.urlProducto("")
				.pc(0.5f)
				.pvp(1)
				.stock(10)
				.tipo(Tipo.BEBIDA)
				.rango(Rango.DIAMANTE)
				.volumen(0.33f)
				.build();servicioProducto.insertar(b4);

		Bebida b5 = Bebida.builder()
				.name("Cerveza")
				.urlProducto("")
				.pc(0.9f)
				.pvp(2)
				.tipo(Tipo.BEBIDA)
				.rango(Rango.PLATINO)
				.volumen(0.5f)
				.build();servicioProducto.insertar(b5);
	}


	//Carga de prueba de los pedidos
	public void cargarPedidos()
	{
		Pedido p1 = Pedido.builder()
				.cliUsername("fernando")
				.empUsername("aAguado69")
				.dateOrdered(LocalDateTime.of(2021,05,18,5,0,0))
				.android(true)
				.price(80)
				.pay(100)
				.exchange(20)
				.notes("")
				.payMethod(MetodoDePago.VISA)
				.payMethod(MetodoDePago.EFECTIVO)
				.product(servicioProducto.buscarPorId(1).get())
				.product(servicioProducto.buscarPorId(6).get())
				.tipo(TipoPedido.DOMICILIO)
				.build();

		servicioPedido.insertar(p1);

		Pedido p2 = Pedido.builder()
				.cliUsername("sara_sal")
				.empUsername("bolas")
				.dateOrdered(LocalDateTime.of(2021,05,18,7,0,0))
				.android(false)
				.price(80)
				.pay(130)
				.exchange(50)
				.notes("")
				.payMethod(MetodoDePago.EFECTIVO)
				.product(servicioProducto.buscarPorId(2).get())
				.product(servicioProducto.buscarPorId(10).get())
				.tipo(TipoPedido.LLEVAR)
				.build();servicioPedido.insertar(p2);

		Pedido p3 = Pedido.builder()
				.cliUsername("pablosalasps")
				.empUsername("RaquelGZ")
				.dateOrdered(LocalDateTime.of(2020,11,23,0,0,0))
				.android(false)
				.price(80)
				.pay(90)
				.exchange(10)
				.notes("para celiacos")
				.payMethod(MetodoDePago.VISA)
				.product(servicioProducto.buscarPorId(3).get())
				.product(servicioProducto.buscarPorId(7).get())
				.tipo(TipoPedido.RECOGER)
				.build();servicioPedido.insertar(p3);

		Pedido p4 = Pedido.builder()
				.cliUsername("pepemartinez")
				.empUsername("mikkelcarballo")
				.dateOrdered(LocalDateTime.of(2020,11,23,0,0,0))
				.android(false)
				.price(80)
				.pay(110)
				.exchange(30)
				.notes("sin pepinillos")
				.payMethod(MetodoDePago.VISA)
				.product(servicioProducto.buscarPorId(4).get())
				.product(servicioProducto.buscarPorId(8).get())
				.tipo(TipoPedido.TOMAR)
				.build();servicioPedido.insertar(p4);

		Pedido p5 = Pedido.builder()
				.cliUsername("john_fred")
				.empUsername("chanaLopez")
				.dateOrdered(LocalDateTime.of(2020,12,8,0,0,0))
				.android(false)
				.price(80)
				.pay(150)
				.exchange(70)
				.notes("entregar al portero")
				.payMethod(MetodoDePago.VISA)
				.product(servicioProducto.buscarPorId(5).get())
				.product(servicioProducto.buscarPorId(9).get())
				.tipo(TipoPedido.DOMICILIO)
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
				.piso("6")
				.puerta("1")
				.build();servicioDireccion.insertar(d2);

		Direccion d3 = Direccion.builder()
				.calle("Jose Isbert")
				.numero("10")
				.portal("2")
				.piso("1")
				.puerta("A")
				.build();servicioDireccion.insertar(d3);

		Direccion d4 = Direccion.builder()
				.calle("Moraleja")
				.numero("124")
				.portal("10")
				.piso("2")
				.puerta("B")
				.build();servicioDireccion.insertar(d4);

		Direccion d5 = Direccion.builder()
				.calle("Oudrid")
				.numero("14")
				.piso("3")
				.puerta("IZQ")
				.build();servicioDireccion.insertar(d5);

		Direccion d6 = Direccion.builder()
				.calle("Los Aluches")
				.numero("20")
				.portal("1")
				.piso("4")
				.puerta("F")
				.build();servicioDireccion.insertar(d6);

		Direccion d7 = Direccion.builder()
				.calle("Ancha")
				.numero("65")
				.portal("3")
				.piso("1")
				.puerta("1")
				.build();servicioDireccion.insertar(d7);

		Direccion d8 = Direccion.builder()
				.calle("Gran Vía")
				.numero("82")
				.piso("5")
				.puerta("C")
				.build();servicioDireccion.insertar(d8);

		Direccion d9 = Direccion.builder()
				.calle("Demonios")
				.numero("66")
				.portal("6")
				.piso("6")
				.puerta("6")
				.build();servicioDireccion.insertar(d9);

		Direccion d10 = Direccion.builder()
				.calle("Anastasia")
				.numero("23")
				.portal("2")
				.piso("6")
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
				.rango(Rango.PLATINO)
				.orders(new HashSet<>())
				.person(servicioPersona.buscarPorEmail("victor.bolagall@hotmail.com").orElse(new Persona()))
				.build();
		servicioUsuario.insertar(u0);


		Usuario u1 = Usuario.builder()
				.username("aAguado69")
				.password("retractil")
				.direccion(servicioDireccion.buscarPorId(2).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.person(servicioPersona.buscarPorEmail("aaguado@yahoo.es").get())
				.build();servicioUsuario.insertar(u1);

		Usuario u2 = Usuario.builder()
				.username("bolas")
				.password("megamega")
				.direccion(servicioDireccion.buscarPorId(3).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.person(servicioPersona.buscarPorEmail("victor.bolagall@hotmail.com").get())
				.build();servicioUsuario.insertar(u2);

		Usuario u3 = Usuario.builder()
				.username("RaquelGZ")
				.password("1234")
				.direccion(servicioDireccion.buscarPorId(4).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.person(servicioPersona.buscarPorEmail("rqraquelita@yahoo.es").get())
				.build();servicioUsuario.insertar(u3);

		Usuario u4 = Usuario.builder()
				.username("mikkelcarballo")
				.password("ratoncitoperez43")
				.direccion(servicioDireccion.buscarPorId(5).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.person(servicioPersona.buscarPorEmail("mikkkkelcarballo@gmail.com").get())
				.build();servicioUsuario.insertar(u4);

		Usuario u5 = Usuario.builder()
				.username("chanaLopez")
				.password("firulaisperrito")
				.direccion(servicioDireccion.buscarPorId(5).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.person(servicioPersona.buscarPorEmail("laurachanalopezzzz@gmail.com").get())
				.build();servicioUsuario.insertar(u5);

		Usuario u6 = Usuario.builder()
				.username("fernando")
				.password("milumilu123")
				.direccion(servicioDireccion.buscarPorId(6).get())
				.rango(Rango.BRONCE)
				.order(servicioPedido.buscarPorId(1).get())
				.person(servicioPersona.buscarPorEmail("fersan88@gmail.com").get())
				.build();servicioUsuario.insertar(u6);

		Usuario u7 = Usuario.builder()
				.username("sara_sal")
				.password("qwerty")
				.direccion(servicioDireccion.buscarPorId(7).get())
				.rango(Rango.BRONCE)
				.order(servicioPedido.buscarPorId(2).get())
				.person(servicioPersona.buscarPorEmail("saritatuloquita@gmail.com").get())
				.build();servicioUsuario.insertar(u7);

		Usuario u8 = Usuario.builder()
				.username("pablosalasps")
				.password("matematico0101")
				.direccion(servicioDireccion.buscarPorId(8).get())
				.rango(Rango.PLATA)
				.order(servicioPedido.buscarPorId(3).get())
				.person(servicioPersona.buscarPorEmail("pablosalasperez@gmail.com").get())
				.build();servicioUsuario.insertar(u8);

		Usuario u9 = Usuario.builder()
				.username("pepemartinez")
				.password("vivaeltecno")
				.direccion(servicioDireccion.buscarPorId(9).get())
				.rango(Rango.BRONCE)
				.order(servicioPedido.buscarPorId(4).get())
				.person(servicioPersona.buscarPorEmail("joselrlr@gmail.com").get())
				.build();servicioUsuario.insertar(u9);

		//john tiene dos usuarios
		Usuario u10a = Usuario.builder()
				.username("john_fred_1993")
				.password("postres?")
				.direccion(servicioDireccion.buscarPorId(10).get())
				.rango(Rango.BRONCE)
				.orders(new HashSet<>())
				.person(servicioPersona.buscarPorEmail("iwanttofreakfree@gmail.com").get())
				.build();servicioUsuario.insertar(u10a);

		Usuario u10b = Usuario.builder()
				.username("john_fred")
				.password("postres?si!")
				.direccion(servicioDireccion.buscarPorId(10).get())
				.rango(Rango.ORO)
				.order(servicioPedido.buscarPorId(5).get())
				.person(servicioPersona.buscarPorEmail("iwanttofreakfree@gmail.com").get())
				.build();servicioUsuario.insertar(u10b);
	}

	//Carga de prueba de los empleados (PERSONA)
	public void cargarEmpleados()
	{
		Empleado e1 = Empleado.builder()
				.dni("07338476g")
				.name("Alejandro").secondName1("Aguado").secondName2("Gutierrez")
				.email("aaguado@yahoo.es")
				.position(Cargo.ENCARGADO)
				.horaEntrada(LocalTime.of(9,30))
				.horaSalida(LocalTime.of(17,30))
				.build();servicioPersona.insertar(e1);

		Empleado e2 = Empleado.builder()
				.dni("84759382n")
				.name("Victor").secondName1("Bolaños").secondName2("Gallego")
				.email("victor.bolagall@hotmail.com")
				.position(Cargo.CAMARERO)
				.horaEntrada(LocalTime.of(9,30))
				.horaSalida(LocalTime.of(15,30))
				.build();servicioPersona.insertar(e2);

		Empleado e3 = Empleado.builder()
				.dni("02958372c")
				.name("Raquel").secondName1("Mosquera").secondName2("López")
				.email("rqraquelita@yahoo.es")
				.position(Cargo.CAMARERO)
				.horaEntrada(LocalTime.of(17,30))
				.horaSalida(LocalTime.of(2,30))
				.build();servicioPersona.insertar(e3);

		Empleado e4 = Empleado.builder()
				.dni("01972239n")
				.name("Mikkel").secondName1("Carballo").secondName2("Puebla")
				.email("mikkkkelcarballo@gmail.com")
				.position(Cargo.REPARTIDOR)
				.horaEntrada(LocalTime.of(15,30))
				.horaSalida(LocalTime.of(0,30))
				.build();servicioPersona.insertar(e4);

		Empleado e5 = Empleado.builder()
				.dni("98374920x")
				.name("Laura").secondName1("Chana").secondName2("López")
				.email("laurachanalopezzzz@gmail.com")
				.position(Cargo.REPARTIDOR)
				.horaEntrada(LocalTime.of(12,30))
				.horaSalida(LocalTime.of(20,30))
				.build();servicioPersona.insertar(e5);

	}

	//Carga de prueba de los clientes (PERSONA)
	public void cargarClientes()
	{
		Cliente c1 = Cliente.builder()
				.dni("75647338q")
				.name("Fernando").secondName1("Sánchez").secondName2("Martinez")
				.email("fersan88@gmail.com")
				//.order()
				.build();servicioPersona.insertar(c1);

		Cliente c2 = Cliente.builder()
				.dni("26396532f")
				.name("Sara").secondName1("Guitierrez").secondName2("Pan")
				.email("saritatuloquita@gmail.com")
				//.order()
				.build();servicioPersona.insertar(c2);

		Cliente c3 = Cliente.builder()
				.dni("01768375c")
				.name("Pablo").secondName1("Salas").secondName2("Perez")
				.email("pablosalasperez@gmail.com")
				//.order()
				.build();servicioPersona.insertar(c3);

		Cliente c4 = Cliente.builder()
				.dni("00239674z")
				.name("Jose Luis").secondName1("Rodriguez").secondName2("Zapatero")
				.email("joselrlr@gmail.com")
				.build();servicioPersona.insertar(c4);

		Cliente c5 = Cliente.builder()
				.dni("98564738d")
				.name("John").secondName1("Fred").secondName2("")
				.email("iwanttofreakfree@gmail.com")
				.build();servicioPersona.insertar(c5);

	}
	
	public void cargarProveedores() {
		Proveedor p1 = Proveedor.builder()
				.nombre("Proveedor1")
				.telefono("911111111")
				.email("proveedor1@gamil.com")
				.calle("Calle1")
				.build();
		servicioProveedor.insertar(p1);
		
		Proveedor p2 = Proveedor.builder()
				.nombre("Proveedor2")
				.telefono("922222222")
				.email("proveedor2@gamil.com")
				.calle("Calle2")
				.build();
		servicioProveedor.insertar(p2);
		
		Proveedor p3 = Proveedor.builder()
				.nombre("Proveedor3")
				.telefono("933333333")
				.email("proveedor3@gamil.com")
				.calle("Calle3")
				.build();
		servicioProveedor.insertar(p3);
		
		Proveedor p4 = Proveedor.builder()
				.nombre("Proveedor4")
				.telefono("944444444")
				.email("proveedor4@gamil.com")
				.calle("Calle4")
				.build();
		servicioProveedor.insertar(p4);
		
		Proveedor p5 = Proveedor.builder()
				.nombre("Proveedor5")
				.telefono("955555555")
				.email("proveedor5@gamil.com")
				.calle("Calle5")
				.build();
		servicioProveedor.insertar(p5);
				
	}

	//envía un emain según los parametros recogidos (destino, titulo y mensaje)
	@PostMapping("/sendemail")
	public ResponseEntity<String> enviarEmail(@RequestBody Map<String,String> mapParams) {

		//a continuacion se insertan el usuario y la contraseña del correo que envia el mensaje
		String usr = "pubsmanagement.vos";
		String pwd = "PubsManagement1234";

		String remitente = "pubsmanagement.vos@gmail.com";

		String destino = mapParams.get("destino");
		String titulo = mapParams.get("titulo");
		String mensaje = mapParams.get("mensaje");
		
		ResponseEntity<String> resp = new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
		HttpStatus htts = HttpStatus.NOT_FOUND;

		if(!destino.equalsIgnoreCase("")){
			htts = HttpStatus.OK;

			/////////////////////////////////////
			try {
				// Se indica el servidor smtp
				/*Properties prop = new Properties();
				prop.put("mail.smtp.auth", true);
				prop.put("mail.smtp.starttls.enable", "true");
				prop.put("mail.smtp.host", "smtp.educa.madrid.org");
				prop.put("mail.smtp.port", "25");
				prop.put("mail.smtp.ssl.trust", "smtp.educa.madrid.org");*/
				 Properties prop = System.getProperties();
				    prop.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
				    prop.put("mail.smtp.user", remitente);
				    prop.put("mail.smtp.clave", pwd);    //La clave de la cuenta
				    prop.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
				    prop.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
				    prop.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

				Session session = Session.getInstance(prop, new Authenticator() {
							@Override
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(usr, pwd);
							}
						}
				);

				// Se construye el mensaje a enviar
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(remitente));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
				message.setSubject(titulo);

				String msg = mensaje;

				//Se indica que el mensaje va a tener varias partes
				//y se crea la parte correspondiente al texto del mensaje
				MimeBodyPart mimeBodyPart = new MimeBodyPart();
				mimeBodyPart.setContent(msg, "text/html");

				//Se añade el texto del mensaje al correo
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(mimeBodyPart);

				message.setContent(multipart);

				// Se envía el mensaje
				//Transport.send(message);
				Transport transport = session.getTransport("smtp");
		        transport.connect("smtp.gmail.com", remitente, pwd);
		        transport.sendMessage(message, message.getAllRecipients());
		        transport.close();

				resp = new ResponseEntity<>("ok", htts);

			} catch(Exception e) {
				System.err.println(e);
				resp = new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
			}
			/////////////////////////////////////


			System.out.println("Email ok");
		}



		return resp;

	}

}
