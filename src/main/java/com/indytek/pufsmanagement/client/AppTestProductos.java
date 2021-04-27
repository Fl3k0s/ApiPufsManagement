package com.indytek.pufsmanagement.client;

import com.indytek.pufsmanagement.model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
/*
Esta clase se encarga de probar funciones relaccionadas con los productos

 */
public class AppTestProductos {

    public static void main(String[] args) {

        listarTodosProductos();

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



}
