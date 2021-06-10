package com.indytek.pufsmanagement.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.indytek.pufsmanagement.controller.PedidoController;
import com.indytek.pufsmanagement.model.*;
import com.indytek.pufsmanagement.servicei.ProductoServiceI;
import com.indytek.pufsmanagement.servicei.UsuarioServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;
/*
Esta clase se encarga de probar los servicios relaccionados con pedido.

 */
public class AppTestPedidos {

    public static void main(String[] args) {

        listarTodosPedidosPorAbierto(true);

        //List<Producto> products = cargarProductos();


        //agregarPedido(products);
        //eliminarPedido();

        //true para indicar que viene de android, false para windows:
        //agregarPedido(products,true);
        //agregarPedido(products,false);

        //Float[] numeritos = facturacion();

        //System.out.println(numeritos[0] + " - " + numeritos[1] + " - " + numeritos[2] + " - " + numeritos[3]);



    }

    //elimina el pedido con user e id
    public static void eliminarPedido(){
        final String URL = "http://localhost:8080/pufs/orders/deleteOrder?user={user}&id={id}";
        RestTemplate restTemplate = new RestTemplate();


        int id = 6;
        String user= "admin";
        try
        {
            restTemplate.put(URL, Usuario.class,user,id);
        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }
    }

    //carga todos los productos con un rango determinado
    public static List<Producto> cargarProductos(){
        final String URL = "http://localhost:8080/pufs/products/getallbyrange?range={rango}";
        RestTemplate restTemplate = new RestTemplate();
        Producto[] prods;
        List<Producto> productos = new ArrayList<>();
        try {
            ResponseEntity<Producto[]> response = restTemplate.getForEntity(URL, Producto[].class, Rango.BRONCE);
            ObjectMapper mapper = new ObjectMapper();
            prods = mapper.convertValue(response.getBody(),Producto[].class);

            productos = Arrays.asList(prods);
        }catch (Exception e){

        }


        return productos;
    }

    //añadir un pedido enviando el pedido construido.
    public static void agregarPedido(List<Producto> products){

        final String URL = "http://localhost:8080/pufs/orders/add";
        RestTemplate restTemplate = new RestTemplate();

        Pedido resp;

        try
        {
            Pedido prods = Pedido.builder()
                    .id(0)
                    .products(products)
                    .android(true)
                    .notes("")
                    .payMethod(MetodoDePago.VISA)
                    .price(0f)
                    .exchange(0f)
                    .cliUsername("admin")
                    .pay(0f)
                    .dateOrdered(LocalDateTime.now())
                    .build();
            Pedido response = restTemplate.postForEntity(URL, prods,Pedido.class).getBody();

            resp = response;

            System.out.println(resp);

        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

    }

    //recoge 4 valores de facturación, costes, ganancias, diferencia y gastos de local.
    public static Float[] facturacion(){

        final String URL = "http://localhost:8080/pufs/orders/billing";
        RestTemplate restTemplate = new RestTemplate();

        Float[] resp = null;

        try
        {

            resp = restTemplate.getForEntity(URL, Float[].class).getBody();

            System.out.println(resp);

        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

        return resp;

    }


    public static void listarTodosPedidosPorAbierto(boolean abierto){

        final String URL = "http://localhost:8080/pufs/orders/getallbyopen?abierto={abierto}";
        RestTemplate restTemplate = new RestTemplate();

        Pedido[] resp;

        try
        {

            ResponseEntity<Pedido[]> response = restTemplate.getForEntity(URL, Pedido[].class,abierto);

            resp = response.getBody();

            for(Pedido p : resp)
                System.out.println(p);

        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

    }

}
