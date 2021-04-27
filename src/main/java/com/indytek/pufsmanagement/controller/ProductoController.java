package com.indytek.pufsmanagement.controller;


import com.indytek.pufsmanagement.model.Producto;
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
    public ResponseEntity<Producto[]> logIn(){

        ResponseEntity<Producto[]> resp;

        List<Producto> products = servicioProducto.buscarTodos();

        Producto[] array = new Producto[products.size()];

        array = products.toArray(array);

        resp = new ResponseEntity<>(array, HttpStatus.OK);

        return resp;
    }

}
