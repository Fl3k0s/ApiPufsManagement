package com.indytek.pufsmanagement.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Rango;
import com.indytek.pufsmanagement.model.Tipo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
/*
Esta clase se encarga de probar funciones relaccionadas con los productos

 */
public class AppTestProductos {

    public static void main(String[] args) {

        System.out.println("/nLista de todos los productos: ");
        listarTodosProductos();

        System.out.println("/nLista de todos los productos por ORO: ");
        listarTodosProductosPorRango(Rango.ORO);

        System.out.println("/nLista de todos los productos por PLATINO: ");
        listarTodosProductosPorRango(Rango.PLATINO);

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

            ResponseEntity<Producto[]> response = restTemplate.getForEntity(URL, Producto[].class,rango);

            ObjectMapper mapper = new ObjectMapper();
            resp = mapper.convertValue(response.getBody(),Producto[].class);

            for(Object p : resp) {
                System.out.println(p.getClass().getName());
                System.out.println(p);
            }
        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

    }

    //Lista de todos los productos que sean del rango intrudcido o menor, y del tipo introducido

    //necesario arreglar la url de esta prueba
    public static void listarTodosProductosPorRango(Rango rango, Tipo tipo){

        final String URL = "http://localhost:8080/pufs/products/getallbyrangetype";
        RestTemplate restTemplate = new RestTemplate();

        Producto[] resp;

        try
        {

            ResponseEntity<Producto[]> response = restTemplate.getForEntity(URL, Producto[].class,rango, tipo);

            ObjectMapper mapper = new ObjectMapper();
            resp = mapper.convertValue(response.getBody(),Producto[].class);

            for(Object p : resp) {
                System.out.println(p.getClass().getName());
                System.out.println(p);
            }
        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

    }



}
