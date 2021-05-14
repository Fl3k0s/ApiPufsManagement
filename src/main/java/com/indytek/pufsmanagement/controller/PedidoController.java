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

        List<Pedido> pedidos = servicioPedido.buscarPorActivo(active);

        Pedido[] array = new Pedido[pedidos.size()];

        array = pedidos.toArray(array);

        resp = new ResponseEntity<>(array, HttpStatus.OK);

        return resp;
    }

    @PostMapping("/add")
    public ResponseEntity<Pedido> agregarPedido(/*RequestParam("user") String user,*/ @RequestBody List<Producto> mapParams){

        ResponseEntity<Pedido> resp;

        Pedido newPedido = Pedido.builder()
                .dateOrdered(LocalDate.now())
                .dateReceived(LocalDate.now())
                //.dateReceived(LocalDate.of()
                .active(true)
                .products(mapParams)
                .build();

        for (Producto p : mapParams){
            System.out.println(p.getClass().getName());
        }
        try{
            servicioPedido.insertar(newPedido);

            Usuario userToAdd = servicioUsuario.buscarPorUsername("admin").get();
            servicioUsuario.actualizar(userToAdd);

            userToAdd.getOrders().add(newPedido);

            servicioUsuario.actualizar(userToAdd);
            resp = new ResponseEntity<>(newPedido, HttpStatus.OK);

            return resp;
        }catch (WrongClassException e){
            e.printStackTrace();
            return new ResponseEntity<>(newPedido, HttpStatus.NOT_FOUND);
        }



    }

    }
