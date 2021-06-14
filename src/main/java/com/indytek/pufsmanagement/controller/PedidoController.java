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

    //FIXME: arreglar bug al iniciar con android
    @PostMapping("/add")
    public ResponseEntity<Pedido> agregarPedido( @RequestBody Pedido pedidoRaw){
        System.out.println("entrando");
        ResponseEntity<Pedido> resp;

        Pedido pedido = pedidoRaw;

        try{

            //recogemos el usuario para introducirle el pedido en la relaccion
            Usuario userToAdd = servicioUsuario.buscarPorUsername(pedidoRaw.getCliUsername()).get();

            pedido = Pedido.builder()
                    .cliUsername(pedidoRaw.getCliUsername())
                    .dateOrdered(LocalDateTime.now())
                    .android(pedidoRaw.isAndroid())
                    .price(pedidoRaw.getPrice())
                    .pay(pedidoRaw.getPay())
                    .exchange(pedidoRaw.getExchange())
                    .notes(pedidoRaw.getNotes())
                    .payMethod(pedidoRaw.getPayMethod())
                    .products(pedidoRaw.getProducts())
                    .tipo(pedidoRaw.getTipo())
                    .abierto(pedidoRaw.isAbierto())
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
            MetodoDePago metod = MetodoDePago.VISA;

            //Lo ponemos en un switch la eleccion del metodo de pago para tener la optimizacion
            //dirigida a siguientes versiones


            switch (pedidoRaw.getPayMethod()){
                case 1:
                    metod = MetodoDePago.VISA;
                    break;
                case 2:
                    metod = MetodoDePago.EFECTIVO;
                    break;
            }

            pedido = Pedido.builder()
                    .cliUsername(pedidoRaw.getUsername())
                    .dateOrdered(LocalDateTime.now())
                    .android(pedidoRaw.isAndroid())
                    .price(pedidoRaw.getPrice())
                    .pay(pedidoRaw.getPay())
                    .exchange(pedidoRaw.getExchange())
                    .notes(pedidoRaw.getNotes())
                    .payMethod(metod)
                    .products(productos)
                    .tipo(TipoPedido.DOMICILIO)
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

    //recibe un user y devuelve todos los pedidos realizador por este.
    @GetMapping("/pedidosForUser")
    public ResponseEntity<List<Pedido>> pedidosPorUsuario(@RequestParam("user")String user){
        Usuario u = servicioUsuario.buscarPorUsername(user).get();
        System.out.println("Hola mundo");

        List<Pedido> pedidos = servicioUsuario.todosLosPedidosDeUnUser(u.getUsername());

        HttpStatus http;
        ResponseEntity<List<Pedido>> resp;
        System.out.println("Hola mundo");

        try{
            resp = new ResponseEntity<>(pedidos, HttpStatus.OK);
            System.out.println("Hola mundo");
            System.out.println(pedidos.size());
            pedidos.forEach(System.out::println);

        }catch (Exception e){
            resp = new ResponseEntity<>(pedidos, HttpStatus.NOT_FOUND);
        }

        return resp;
    }

    //envia un array de 4 float que resumen los gastos del dia. (de 2am a 2am)
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

    //devuelve el ultimo pedido realizado desde android.
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

    //elimina un pedido.
    @PutMapping("/deleteOrder")
    public ResponseEntity<Pedido> deleteOrder(@RequestParam("user") String user, @RequestParam("id") int id){
        System.out.println("antes de borrar");

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
            System.out.println("uy, he fallado");
            http = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(pedido, http);
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
    
    //recoge todos los pedidos realizados el dia de hoy
    @GetMapping("/getalltoday")
    public ResponseEntity<Pedido[]> listarHoy(){

        ResponseEntity<Pedido[]> resp;

        Pedido[] array = new Pedido[0];

        try {

            List<Pedido> pedidos = servicioPedido.buscarTodosHoy();

            array = new Pedido[pedidos.size()];

            array = pedidos.toArray(array);

            resp = new ResponseEntity<>(array, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(array, HttpStatus.NOT_FOUND);

        }

        return resp;

    }

    //devuelve todos los pedidos.
    @GetMapping("/getall")
    public ResponseEntity<Pedido[]> listarTodo(){

        ResponseEntity<Pedido[]> resp;

        Pedido[] array = new Pedido[0];

        try {

            List<Pedido> pedidos = servicioPedido.buscarTodos();

            array = new Pedido[pedidos.size()];

            array = pedidos.toArray(array);

            resp = new ResponseEntity<>(array, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(array, HttpStatus.NOT_FOUND);

        }

        return resp;

    }

    //devuelve un pedido a partir de su id
    @GetMapping("/get")
    public ResponseEntity<Pedido> buscarPedido(@RequestParam int id){

        ResponseEntity<Pedido> resp;
        Pedido pedido = null;

        try {

            pedido = servicioPedido.buscarPorId(id).get();

            resp = new ResponseEntity<>(pedido, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(pedido, HttpStatus.NOT_FOUND);

        }

        return resp;

    }
    
    @GetMapping("/getAllForType")
    public ResponseEntity<List<Pedido>> buscarPedidoPorTipo(@RequestParam String tipo){

        ResponseEntity<List<Pedido>> resp;
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try {
        	TipoPedido tp = null;
            for (TipoPedido tipoPedido : TipoPedido.values())
            {
                if (tipo.toUpperCase().contains(tipoPedido.name())) {
                    tp = tipoPedido;
                }
            }
        	
            pedidos = servicioPedido.buscarPorTipo(tp);

            resp = new ResponseEntity<>(pedidos, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(pedidos, HttpStatus.NOT_FOUND);

        }

        return resp;

    }

    @GetMapping("/getallbyopen")
    public ResponseEntity<List<Pedido>> buscarPedidoPorAbierto(@RequestParam("abierto") boolean abierto){

        ResponseEntity<List<Pedido>> resp;
        List<Pedido> pedidos = new ArrayList<Pedido>();

        try {

            pedidos = servicioPedido.buscarPorAbierto(abierto);

            resp = new ResponseEntity<>(pedidos, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(pedidos, HttpStatus.NOT_FOUND);

        }

        return resp;

    }

    //metodo para actualizar pedido, si el pedido no ha podido ser actualizado, el pedido devuelto tendrá id = 0
    @PutMapping("/update")
    public ResponseEntity<Pedido> actualizarPedido(@RequestBody Pedido pedido){
        HttpStatus status = HttpStatus.ACCEPTED;

        if (!servicioPedido.actualizar(pedido))
			status = HttpStatus.BAD_REQUEST;
        
		return new ResponseEntity<Pedido>(pedido,status);

    }
}
