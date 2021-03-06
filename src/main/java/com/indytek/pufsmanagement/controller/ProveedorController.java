package com.indytek.pufsmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indytek.pufsmanagement.model.Proveedor;
import com.indytek.pufsmanagement.servicei.ProveedorServiceI;

@RestController
@RequestMapping("pufs/provider")

/*
Controlador de los proveedores,  En esta clase se encontrarán los metodos rest que son llamados por las aplicaciones.

 */
public class ProveedorController {
	@Autowired ProveedorServiceI servicioProveedor;

	//devuelve todos los proveedores.
	@GetMapping("/getall")
    public ResponseEntity<Proveedor[]> listarTodo(){

        ResponseEntity<Proveedor[]> resp;

        Proveedor[] array = new Proveedor[0];

        try {

            List<Proveedor> proveedores = servicioProveedor.buscarTodos();

            array = new Proveedor[proveedores.size()];

            array = proveedores.toArray(array);

            resp = new ResponseEntity<>(array, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(array, HttpStatus.NOT_FOUND);

        }

        return resp;

    }

    //debvuelve un proveedor a partir de su id
    @GetMapping("/get")
    public ResponseEntity<Proveedor> buscarPedido(@RequestParam int id){

        ResponseEntity<Proveedor> resp;
        Proveedor proveedor = null;

        try {

        	proveedor = servicioProveedor.buscarPorId(id).get();

            resp = new ResponseEntity<>(proveedor, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(proveedor, HttpStatus.NOT_FOUND);

        }

        return resp;

    }

    //devuelve un proveedor a partir de su nombre
    @GetMapping("/getByName")
    public ResponseEntity<Proveedor> buscarPedido(@RequestParam String name){

        ResponseEntity<Proveedor> resp;
        Proveedor proveedor = null;

        try {

        	proveedor = servicioProveedor.buscarPorNombre(name).get();

            resp = new ResponseEntity<>(proveedor, HttpStatus.OK);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(proveedor, HttpStatus.NOT_FOUND);

        }

        return resp;

    }

}
