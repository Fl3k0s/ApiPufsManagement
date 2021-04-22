package com.indytek.pufsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indytek.pufsmanagement.servicei.PedidoServiceI;
import com.indytek.pufsmanagement.servicei.PersonaServiceI;
import com.indytek.pufsmanagement.servicei.ProductoServiceI;

@RestController
@RequestMapping("pufs/test")
public class CargarDatosController {
	
	@Autowired PedidoServiceI servicioPedido;
	@Autowired PersonaServiceI servicioPersona;
	@Autowired ProductoServiceI servicioProducto;
	
	
	@GetMapping("/installtest")
	public ResponseEntity<String> cargarDatos ()
	{
		ResponseEntity <String> response;

		
		
		
		
		
		
		
	
		//necesario comprobar si este mensaje aparece para crear datos por defecto
		response =  new ResponseEntity<>("<h1>Carga realizada correctamente</h1>", HttpStatus.OK);
		
		return response;
	}
		
}
