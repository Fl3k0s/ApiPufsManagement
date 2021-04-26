package com.indytek.pufsmanagement.controller;


import com.indytek.pufsmanagement.servicei.PedidoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pufs/products")
public class ProductoController {

    @Autowired
    PedidoServiceI servicioPedido;

}
