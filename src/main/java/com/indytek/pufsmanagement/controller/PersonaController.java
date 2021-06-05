package com.indytek.pufsmanagement.controller;


import com.indytek.pufsmanagement.model.Cargo;
import com.indytek.pufsmanagement.model.Empleado;
import com.indytek.pufsmanagement.model.Persona;
import com.indytek.pufsmanagement.model.Usuario;
import com.indytek.pufsmanagement.servicei.EmpleadoServiceI;
import com.indytek.pufsmanagement.servicei.PedidoServiceI;
import com.indytek.pufsmanagement.servicei.PersonaServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.List;
import java.util.ArrayList;
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
    @Autowired
    EmpleadoServiceI servicioEmpleado;

    @GetMapping("/listaEmpleados")
    public ResponseEntity<List<Empleado>> listaEmpleados(@RequestParam("Cargo") String cargo){

        ResponseEntity<List<Empleado>> resp;

        try {
            Cargo c = null;
            for (Cargo carg : Cargo.values())
            {
                System.out.println(carg.name());
                System.out.println(cargo.toUpperCase());
                if (cargo.toUpperCase().contains(carg.name())) {
                    c = carg;
                }
            }

            List<Empleado> empleados = servicioEmpleado.listarEmpleadoPorPosicion(c);
            HttpStatus htts = HttpStatus.OK;

            resp = new ResponseEntity<>(empleados, htts);

        }catch(Exception e){

            e.printStackTrace();
            List<Empleado> empleados = new ArrayList<Empleado>();
            resp = new ResponseEntity<>(empleados, HttpStatus.NOT_FOUND);

        }

        return resp;
    }

}
