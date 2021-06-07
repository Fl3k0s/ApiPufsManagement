package com.indytek.pufsmanagement.controller;


import com.indytek.pufsmanagement.model.*;
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

    //devuelve la lista completa de personas empleados
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

    @GetMapping("/getemployee")
    public ResponseEntity<Empleado> buscarEmpleado(@RequestParam int id){

        ResponseEntity<Empleado> resp;
        Empleado empleado = Empleado.builder().id(0).build();

        try {

            //si no es empleado, devolverá un empleado de id 0.
            empleado = (Empleado) servicioPersona.buscarPorId(id).get();

            resp = new ResponseEntity<>(empleado, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(empleado, HttpStatus.NOT_FOUND);

        }

        return resp;

    }

    @PostMapping("/addemployee")
    public ResponseEntity<Empleado> insertarEmpleado(@RequestBody Empleado empleado){

        ResponseEntity<Empleado> resp;

        try{

            servicioPersona.insertar(empleado);
            resp = new ResponseEntity<>(empleado, HttpStatus.OK);
        }
        catch(Exception e){


            resp = new ResponseEntity<>(empleado, HttpStatus.NOT_FOUND);
        }

        return resp;
    }

}
