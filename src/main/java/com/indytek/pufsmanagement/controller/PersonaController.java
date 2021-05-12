package com.indytek.pufsmanagement.controller;


import com.indytek.pufsmanagement.model.Usuario;
import com.indytek.pufsmanagement.servicei.PedidoServiceI;
import com.indytek.pufsmanagement.servicei.PersonaServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

@RestController
@RequestMapping("pufs/people")
/*
Controlador de las personas, ya sean empleados o clientes. En esta clase se encontrarán los metodos rest que son llamados por las aplicaciones

 */
public class PersonaController {

    @Autowired
    PersonaServiceI servicioPersona;

}
