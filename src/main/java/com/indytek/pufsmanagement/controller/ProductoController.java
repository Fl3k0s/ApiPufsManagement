package com.indytek.pufsmanagement.controller;


import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Rango;
import com.indytek.pufsmanagement.model.Usuario;
import com.indytek.pufsmanagement.servicei.PedidoServiceI;
import com.indytek.pufsmanagement.servicei.ProductoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pufs/products")
/*
Controlador de los productos, tanto comidas como bebidas. En esta clase se encontrarán los metodos rest que son llamados por las aplicaciones

 */
public class ProductoController {

    @Autowired
    ProductoServiceI servicioProducto;

    //metodo encargado de recoger todos los productos y devolverlos como un array a la aplicacion
    @GetMapping("/getall")
    public ResponseEntity<Producto[]> listarTodosProductos(){

        ResponseEntity<Producto[]> resp;

        Producto[] array = new Producto[0];

        try {

            List<Producto> products = servicioProducto.buscarTodos();

            array = new Producto[products.size()];

            array = products.toArray(array);

            resp = new ResponseEntity<>(array, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(array, HttpStatus.NOT_FOUND);

        }

        return resp;
    }

    @GetMapping("/getallbyrange")
    public ResponseEntity<List<Producto>> listarTodosProductosPorRango(@RequestParam("range") Rango rango){

        ResponseEntity<List<Producto>> resp;

        List<Producto> products = new ArrayList<>();

        try {

            products = servicioProducto.buscarPorRango(rango);

            Producto[] array = new Producto[products.size()];

            array = products.toArray(array);

            resp = new ResponseEntity<>(products, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(products, HttpStatus.OK);

        }

        return resp;
    }

}
