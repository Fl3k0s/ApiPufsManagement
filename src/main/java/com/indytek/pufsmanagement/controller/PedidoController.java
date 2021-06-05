package com.indytek.pufsmanagement.controller;


import com.indytek.pufsmanagement.model.*;
import com.indytek.pufsmanagement.servicei.PedidoServiceI;
import com.indytek.pufsmanagement.servicei.ProductoServiceI;
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
    @Autowired ProductoServiceI servicioProducto;
    @Autowired UsuarioServiceI servicioUsuario;

    public static List<Pedido> cancel = new ArrayList<>();

    /*
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
    */

    //FIXME: arreglar bug al iniciar con android
    @PostMapping("/add")
    public ResponseEntity<Pedido> agregarPedido(/*RequestParam("user") String user,*/ @RequestBody Pedido pedidoRaw){

        ResponseEntity<Pedido> resp;

        Pedido pedido = pedidoRaw;

        try{

            //recogemos el usuario para introducirle el pedido en la relaccion
            Usuario userToAdd = servicioUsuario.buscarPorUsername(pedidoRaw.getCliUsername()).get();

            pedido = Pedido.builder()
                    .cliUsername(pedidoRaw.getCliUsername())
                    .dateOrdered(pedidoRaw.getDateOrdered())
                    .android(pedidoRaw.isAndroid())
                    .price(pedidoRaw.getPrice())
                    .pay(pedidoRaw.getPay())
                    .exchange(pedidoRaw.getExchange())
                    .notes(pedidoRaw.getNotes())
                    .payMethod(pedidoRaw.getPayMethod())
                    .products(pedidoRaw.getProducts())
                    .build();servicioPedido.insertar(pedido);

            userToAdd.getOrders().add(pedido);

            servicioUsuario.actualizar(userToAdd);

            servicioPedido.buscarTodos().forEach(System.out::println);
            resp = new ResponseEntity<>(pedido, HttpStatus.OK);

            servicioUsuario.buscarPorUsername(pedido.getCliUsername()).get().getOrders().forEach(System.out::println);

        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseEntity<>(pedido, HttpStatus.NOT_FOUND);
        }

        return resp;


    }

    //CHAPUZA
    @PostMapping("/addAndroid")
    public ResponseEntity<Pedido> agregarPedidoAndroid(/*RequestParam("user") String user,*/ @RequestBody PedidoSerialize pedidoRaw){

        ResponseEntity<Pedido> resp;

        Pedido pedido = new Pedido();

        List<Producto> productos = new ArrayList<>();

        try{

            //recogemos el usuario para introducirle el pedido en la relaccion
            Usuario userToAdd = servicioUsuario.buscarPorUsername(pedidoRaw.getUsername()).get();

            for (int i : pedidoRaw.getProducts()){
                productos.add(servicioProducto.buscarPorId(i).get());
            }


            pedido = Pedido.builder()
                    .cliUsername(pedidoRaw.getUsername())
                    .dateOrdered(pedidoRaw.getDateOrdered())
                    .android(pedidoRaw.isAndroid())
                    .price(pedidoRaw.getPrice())
                    .pay(pedidoRaw.getPay())
                    .exchange(pedidoRaw.getExchange())
                    .notes(pedidoRaw.getNotes())
                    .payMethod(null)
                    .products(productos)
                    .build();

            servicioPedido.insertar(pedido);

            userToAdd.getOrders().add(pedido);

            servicioUsuario.actualizar(userToAdd);

            servicioPedido.buscarTodos().forEach(System.out::println);
            resp = new ResponseEntity<>(pedido, HttpStatus.OK);

            servicioUsuario.buscarPorUsername(pedido.getCliUsername()).get().getOrders().forEach(System.out::println);

        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseEntity<>(pedido, HttpStatus.NOT_FOUND);
        }

        return resp;


    }

    @GetMapping("/pedidosForUser")
    public ResponseEntity<List<Pedido>> pedidosPorUsuario(@RequestParam("user")String user){
        Usuario u = servicioUsuario.buscarPorUsername(user).get();

        List<Pedido> pedidos = servicioUsuario.todosLosPedidosDeUnUser(u.getUsername());

        HttpStatus http;
        ResponseEntity<List<Pedido>> resp;

        try{
            resp = new ResponseEntity<>(pedidos, HttpStatus.OK);
        }catch (Exception e){
            resp = new ResponseEntity<>(pedidos, HttpStatus.NOT_FOUND);
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

    @PutMapping("/deleteOrder")
    public ResponseEntity<Usuario> deleteOrder(@RequestParam("user") String user, @RequestParam("id") int id){
        Pedido pedido = new Pedido();
        HttpStatus http = HttpStatus.OK;
        Usuario u = servicioUsuario.buscarPorUsername(user).get();

        try {

            System.out.println("antes de borrar");
            pedido = servicioPedido.buscarPorId(id).get();

            u = servicioUsuario.quitarPedido(user, id);
            cancel.add(servicioPedido.buscarPorId(id).get());
            servicioUsuario.actualizar(u);

            System.out.println("borrado");



        }catch (Exception e){
            System.out.println("uy, he fallado jijiij");
            http = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(u, http);
    }

    @GetMapping("/canceleds")
    public ResponseEntity<Pedido> cancelOrder(){
        Pedido pedido;
        if (cancel.isEmpty())
            pedido = new Pedido();
        else
            pedido=cancel.get(cancel.size() - 1);

        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

}
