package com.indytek.pufsmanagement.controller;

import com.indytek.pufsmanagement.model.TipoUsuario;
import com.indytek.pufsmanagement.model.Usuario;
import com.indytek.pufsmanagement.servicei.UsuarioServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("pufs/users")
public class UsuarioController {

    @Autowired UsuarioServiceI servicioUsuario;

    @GetMapping("/login")
    public ResponseEntity<Usuario> logIn(@RequestParam String username, @RequestParam String password){

        ResponseEntity<Usuario> resp;
        Optional<Usuario> user = servicioUsuario.buscarPorUsername(username);
        HttpStatus htts = HttpStatus.NOT_FOUND;

        if(servicioUsuario.comprobarInicioSesion(username, password)) {
            htts = HttpStatus.OK;
            System.out.println("Log in success");
        }

        resp = new ResponseEntity<>(user.get(), htts);

        return resp;
    }

    @PostMapping("/clientsignup")
    public ResponseEntity<Usuario> clientSignUp(@RequestParam String username, @RequestParam String password){

        ResponseEntity<Usuario> resp;
        Optional<Usuario> user = servicioUsuario.buscarPorUsername(username);
        HttpStatus htts = HttpStatus.NOT_FOUND;

        if(user.isEmpty()){
            htts = HttpStatus.OK;
            Usuario newUser = Usuario.builder()
                    .username(username)
                    .password(password)
                    .usertype(TipoUsuario.CLIENTE)
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
