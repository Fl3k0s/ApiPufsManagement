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
import java.util.*;

public class AppTestPedidos {

    public static void main(String[] args) {

        //OK: listarTodosPedidosPorActivo(true);

        //List<Producto> products = cargarProductos();

        //true para indicar que viene de android, false para windows:
        //agregarPedido(products,true);
        //agregarPedido(products,false);

        //Float[] numeritos = facturacion();

        //System.out.println(numeritos[0] + " - " + numeritos[1] + " - " + numeritos[2] + " - " + numeritos[3]);



    }

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
    //Lista de todos los pedidos que cuimplan con activo true o false
    public static void listarTodosPedidosPorActivo(boolean active){

        final String URL = "http://localhost:8080/pufs/orders/getallbyactive?active={active}";
        RestTemplate restTemplate = new RestTemplate();

        Pedido[] resp;

        try
        {

            ResponseEntity<Pedido[]> response = restTemplate.getForEntity(URL, Pedido[].class,active);

            resp = response.getBody();

            for(Pedido p : resp)
                System.out.println(p);

        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

    }

    public static void agregarPedido(List<Producto> products){

        final String URL = "http://localhost:8080/pufs/orders/add";
        RestTemplate restTemplate = new RestTemplate();

        Pedido resp;

        try
        {
            Producto[] prods = products.toArray(new Producto[0]);
            Pedido response = restTemplate.postForEntity(URL, prods,Pedido.class).getBody();

            resp = response;

            System.out.println(resp);

        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

    }

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

    public static Pedido ultimoPedidoAndroidHoy(){

        final String URL = "http://localhost:8080/pufs/orders/lastandroidtoday";
        RestTemplate restTemplate = new RestTemplate();

        Pedido resp = null;

        try
        {

            resp = restTemplate.getForEntity(URL, Pedido.class).getBody();

            System.out.println(resp);

        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

        return resp;

    }


}
