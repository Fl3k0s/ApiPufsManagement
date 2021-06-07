package com.indytek.pufsmanagement.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
/*
Esta clase se encarga de probar la carga de datos. Genera un esquema de datos completo de clientes, productos, usuarios, direcciones y pedidos

 */
public class AppCargarDatos {

	public static void main(String[] args) {

		cargarDatos();

	}

	//carga de togos los datos
	public static void cargarDatos ()
	{
		final String URL = "http://localhost:8080/pufs/test/installtest";
		RestTemplate restTemplate = new RestTemplate();
		
		try 
		{
			ResponseEntity<String> response  = restTemplate.getForEntity(URL, String.class);
			
			System.out.println(response.getBody());
		}
		
		catch(HttpClientErrorException e)
		{
			System.out.println ("error");
		}
	}

}
