package com.indytek.pufsmanagement.client;

import com.indytek.pufsmanagement.model.Pedido;
import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Rango;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class AppTestPedidos {

    public static void main(String[] args) {

        listarTodosPedidosPorActivo(true);

        //agregarPedido();

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

}
