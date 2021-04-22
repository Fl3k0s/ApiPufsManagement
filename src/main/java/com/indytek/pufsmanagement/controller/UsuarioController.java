package com.indytek.pufsmanagement.controller;

import com.indytek.pufsmanagement.model.Usuario;
import com.indytek.pufsmanagement.servicei.UsuarioServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("pufs/users")
public class UsuarioController {

    @Autowired UsuarioServiceI servicioUsuario;

    @GetMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam String username, @RequestParam String password){

        ResponseEntity<Usuario> resp;
        Optional<Usuario> user = servicioUsuario.buscarPorUsername(username);
        HttpStatus htts = HttpStatus.NOT_FOUND;

        if(servicioUsuario.comprobarInicioSesion(username, password))
            htts = HttpStatus.OK;

        resp = new ResponseEntity<Usuario>(user.get(),htts);

        return resp;
    }

}
