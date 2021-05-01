package com.indytek.pufsmanagement.client;

import com.indytek.pufsmanagement.controller.PedidoController;
import com.indytek.pufsmanagement.model.*;
import com.indytek.pufsmanagement.servicei.ProductoServiceI;
import com.indytek.pufsmanagement.servicei.UsuarioServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class AppTestPedidos {

    public static void main(String[] args) {

        //OK: listarTodosPedidosPorActivo(true);

        ArrayList<Producto> products = new ArrayList<>();
        products.add(Comida.builder()
                .name("Perrito")
                .urlProducto("")
                .pvp(6)
                .tipo(Tipo.ENTRANTE)
                .rango(Rango.DIAMANTE)
                .kg(0.8f)
                .build());
        products.add(Bebida.builder()
                .name("Coca-Cola")
                .urlProducto("")
                .pvp(8)
                .tipo(Tipo.BEBIDA)
                .rango(Rango.BRONCE)
                .uds(20)
                .build());

        Usuario user = Usuario.builder()
                .username("chanaLopez")
                .password("firulaisperrito")
                .direccion(Direccion.builder()
                        .calle("Oudrid")
                        .numero("14")
                        .piso(3)
                        .puerta("IZQ")
                        .build())
                .rango(Rango.BRONCE)
                .orders(new HashSet<>())
                .build();

        agregarPedido(user,products);

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

    public static void agregarPedido(Usuario user, List<Producto> products){

        final String URL = "http://localhost:8080/pufs/orders/add";
        RestTemplate restTemplate = new RestTemplate();

        Pedido resp;

        Map<String, List<Producto>> mapParams = new HashMap<String, List<Producto>>();
        mapParams.put("products", products);

        try
        {

            Pedido response = restTemplate.postForObject(URL, user, Pedido.class, mapParams);

            resp = response;

            System.out.println(resp);

        }

        catch(HttpClientErrorException e)
        {
            System.out.println("error");
        }

    }

}
