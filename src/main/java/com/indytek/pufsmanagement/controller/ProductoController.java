package com.indytek.pufsmanagement.controller;


import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Rango;
import com.indytek.pufsmanagement.model.Tipo;
import com.indytek.pufsmanagement.servicei.ProductoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    //devuelve todos los productos que sean del rango recogido o menor.
    @GetMapping("/getallbyrange")
    public ResponseEntity<List<Producto>> listarTodosProductosPorRango(@RequestParam("range") Rango rango){

        ResponseEntity<List<Producto>> resp;

        List<Producto> products = new ArrayList<>();

        try {

            products = servicioProducto.buscarPorRango(rango);

            Producto[] array = new Producto[products.size()];

            resp = new ResponseEntity<>(products, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(products, HttpStatus.OK);

        }

        return resp;
    }

    //devuelve todos los productos que sean del rango recogido o menor y que coincidan con el tipo.
    @GetMapping("/getallbyrangetype")
    public ResponseEntity<List<Producto>> listarTodosProductosPorRangoTipo(@RequestParam("rango") Rango rango, @RequestParam("tipo") Tipo tipo){

        ResponseEntity<List<Producto>> resp;

        List<Producto> products = new ArrayList<>();

        System.out.println("Productos");

        try {

            products = servicioProducto.buscarPorRango(rango);

            products = products.stream()
                    .filter(p -> p.getTipo().equals(tipo))
                    .collect(Collectors.toList());

            Producto[] array = new Producto[products.size()];

            products.forEach(System.out::println);
            array = products.toArray(array);

            resp = new ResponseEntity<>(products, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(products, HttpStatus.OK);

        }

        return resp;
    }

}
