package com.indytek.pufsmanagement.controller;


import com.indytek.pufsmanagement.servicei.PedidoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pufs/people")
/*
Controlador de las personas, ya sean empleados o clientes. En esta clase se encontrarán los metodos rest que son llamados por las aplicaciones

 */
public class PersonaController {

    @Autowired
    PedidoServiceI servicioPedido;

}
