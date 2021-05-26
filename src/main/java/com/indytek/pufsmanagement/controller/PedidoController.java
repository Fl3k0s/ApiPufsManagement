package com.indytek.pufsmanagement.controller;


import com.indytek.pufsmanagement.model.Pedido;
import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Usuario;
import com.indytek.pufsmanagement.servicei.PedidoServiceI;
import com.indytek.pufsmanagement.servicei.UsuarioServiceI;
import org.hibernate.WrongClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("pufs/orders")
/*
Controlador de los pedidos. En esta clase se encontrarán los metodos rest que son llamados por las aplicaciones

 */
public class PedidoController {

    @Autowired PedidoServiceI servicioPedido;
    @Autowired UsuarioServiceI servicioUsuario;

    @GetMapping("/getallbyactive")
    public ResponseEntity<Pedido[]> listarPorActivo(@RequestParam("active") boolean active){

        ResponseEntity<Pedido[]> resp;

        Pedido[] array = new Pedido[0];

        try {

            List<Pedido> pedidos = servicioPedido.buscarPorActivo(active);

            array = new Pedido[pedidos.size()];

            array = pedidos.toArray(array);

            resp = new ResponseEntity<>(array, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(array, HttpStatus.NOT_FOUND);

        }

        return resp;

    }

    @PostMapping("/add")
    public ResponseEntity<Pedido> agregarPedido(/*RequestParam("user") String user,*/ @RequestBody Producto[] mapParams, @RequestParam("android") Boolean android){

        ResponseEntity<Pedido> resp;

        //calculamos el precio total del pedido
        var price = Arrays.stream(mapParams).map(Producto::getPvp).reduce((a,b) -> a + b).orElse(0f);


        Pedido newPedido = Pedido.builder()
                .dateOrdered(LocalDateTime.now())
                .android(false)
                .products(Arrays.asList(mapParams))
                .price(price)
                .build();

        for (Producto p : mapParams){
            System.out.println(p.getClass().getName());
        }
        try{

            Usuario userToAdd = servicioUsuario.buscarPorUsername("admin").get();

            userToAdd.getOrders().add(newPedido);

            servicioUsuario.actualizar(userToAdd);
            servicioPedido.insertar(newPedido);

            //FIXME: dejar de momento comentado hasta solucion del bug
            //servicioPedido.borrarNulos();

            //muestra todos los pedidos en la bbdd
            servicioPedido.buscarTodos().forEach(System.out::println);

            //los pedidos de este usuario
            System.out.println("PEDIDOS DE ESTE USUARIO");
            servicioUsuario.buscarPorUsername("admin").get().getOrders().forEach(System.out::println);
            resp = new ResponseEntity<>(newPedido, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseEntity<>(newPedido, HttpStatus.NOT_FOUND);
        }

        return resp;


    }

    @GetMapping("/billing")
    public ResponseEntity<Float[]> facturacion(){

        ResponseEntity<Float[]> resp;

        Float[] array = new Float[4];

        try {

            array[0] = servicioPedido.gastoTotalHoy();
            array[1] = servicioPedido.gastoGestionHoy();
            array[2] = servicioPedido.beneficioTotalHoy();
            array[3] = servicioPedido.beneficioRealHoy();


            resp = new ResponseEntity<>(array, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(array, HttpStatus.NOT_FOUND);

        }

        return resp;

    }

    @GetMapping("/lastandroidtoday")
    public ResponseEntity<Pedido> ultimoAndroidHoy(){

        ResponseEntity<Pedido> resp;

        Pedido pedido = null;

        try {

            resp = new ResponseEntity<>(pedido, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(Pedido.builder().id(0).build(), HttpStatus.NOT_FOUND);

        }

        return resp;

    }



    }
