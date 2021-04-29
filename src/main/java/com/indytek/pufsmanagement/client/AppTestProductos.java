package com.indytek.pufsmanagement.client;

import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Rango;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/*
Esta clase se encarga de probar funciones relaccionadas con los productos

 */
public class AppTestProductos {

    public static void main(String[] args) {

        System.out.println("/nLista de todos los productos: ");
        listarTodosProductos();

        System.out.println("/nLista de todos los productos por ORO: ");
        listarTodosProductosPorRango(Rango.ORO);

        System.out.println("/nLista de todos los productos por DIAMANTE: ");
        listarTodosProductosPorRango(Rango.ORO);

    }

    //Lista de todos los productos
    public static void listarTodosProductos(){

        final String URL = "http://localhost:8080/pufs/products/getall";
        RestTemplate restTemplate = new RestTemplate();

        Producto[] resp;

        try
        {
            ResponseEntity<Producto[]> response = restTemplate.getForEntity(URL, Producto[].class);

            resp = response.getBody();

            for(Producto p : resp)
                System.out.println(p);

        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

    }

    //Lista de todos los productos que sean del rango intrudcido o menor
    public static void listarTodosProductosPorRango(Rango rango){

        final String URL = "http://localhost:8080/pufs/products/getallbyrange?range={rango}";
        RestTemplate restTemplate = new RestTemplate();

        Producto[] resp;

        try
        {

            HashMap<String,Rango> params = new HashMap<String,Rango>();
            params.put("range",rango);

            ResponseEntity<Producto[]> response = restTemplate.getForEntity(URL, Producto[].class,params);

            resp = response.getBody();

            for(Producto p : resp)
                System.out.println(p);

        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

    }



}
