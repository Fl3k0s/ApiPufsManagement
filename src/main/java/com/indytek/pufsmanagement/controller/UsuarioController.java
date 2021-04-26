package com.indytek.pufsmanagement.controller;

import com.indytek.pufsmanagement.model.Direccion;
import com.indytek.pufsmanagement.model.Rango;
import com.indytek.pufsmanagement.model.Usuario;
import com.indytek.pufsmanagement.servicei.UsuarioServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;

@RestController
@RequestMapping("pufs/users")
public class UsuarioController {

    @Autowired UsuarioServiceI servicioUsuario;

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

    @PostMapping("/clientsignup")
    public ResponseEntity<Usuario> clientSignUp(@RequestBody Usuario u){

        ResponseEntity<Usuario> resp;
        Optional<Usuario> user = servicioUsuario.buscarPorUsername(u.getUsername());
        HttpStatus htts = HttpStatus.NOT_FOUND;

        //el cliente se registra sin definir la direccion, pero no podra realizar pedidos hasta que la defina dentro de la app.

        if(user.isEmpty()){
            htts = HttpStatus.OK;
            Usuario newUser = Usuario.builder()
                    .username(u.getUsername())
                    .password(u.getPassword())
                    .build();
            servicioUsuario.insertar(newUser);

            //se rellena el optional para devolverlo en el response
            user = Optional.of(newUser);
            System.out.println("Sign up success");
        }

        resp = new ResponseEntity<>(user.get(), htts);

        return resp;
    }


}
