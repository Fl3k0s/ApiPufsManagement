package com.indytek.pufsmanagement.controller;

import com.indytek.pufsmanagement.model.Direccion;
import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Rango;
import com.indytek.pufsmanagement.model.Usuario;
import com.indytek.pufsmanagement.servicei.UsuarioServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*;

import java.util.*;

@RestController
@RequestMapping("pufs/users")
/*
Controlador de los usuarios, ya sean empleados o clientes. En esta clase se encontrarán los metodos rest que son llamados por las aplicaciones

 */
public class UsuarioController {

    @Autowired UsuarioServiceI servicioUsuario;

    //Metodo que realiza el inicio de sesion, recogiendo los parametros 'username' y 'password' y comprobandolos con la api.
    //si el inicio no es satisfactorio, el usuario devuelto será null.
    @GetMapping("/login")
    public ResponseEntity<Usuario> logIn(@RequestParam("username") String username, @RequestParam("password") String password){

        ResponseEntity<Usuario> resp;
        Optional<Usuario> user = servicioUsuario.buscarPorUsername(username);
        HttpStatus htts = HttpStatus.NOT_FOUND;

        if(servicioUsuario.comprobarInicioSesion(username, password)) {
            htts = HttpStatus.OK;
            System.out.println("Log in success");
        }
        Usuario u = user.get();
        resp = new ResponseEntity<>(u, htts);

        return resp;
    }

    //Metodo que realiza el registro de nuevos usuarios.
    //recogiendo los parametros 'username' y 'password' y comprobandolos con la api.
    @PostMapping("/clientsignup")
    public ResponseEntity<Usuario> clientSignUp(@RequestBody Usuario user){

        ResponseEntity<Usuario> resp;
        Optional<Usuario> newUser = servicioUsuario.buscarPorUsername(user.getUsername());
        HttpStatus htts = HttpStatus.NOT_FOUND;

        if(newUser.isEmpty()){
            htts = HttpStatus.OK;
            servicioUsuario.insertar(user);

            System.out.println("Sign up success");
        }

        resp = new ResponseEntity<>(user, htts);

        return resp;
    }

}
