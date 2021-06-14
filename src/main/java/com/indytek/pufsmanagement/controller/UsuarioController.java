package com.indytek.pufsmanagement.controller;

import com.indytek.pufsmanagement.model.*;
import com.indytek.pufsmanagement.servicei.EmpleadoServiceI;
import com.indytek.pufsmanagement.servicei.PersonaServiceI;
import com.indytek.pufsmanagement.servicei.UsuarioServiceI;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import javax.mail.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.mail.internet.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pufs/users")
/*
Controlador de los usuarios, ya sean empleados o clientes. En esta clase se encontrarán los metodos rest que son llamados por las aplicaciones

 */
public class UsuarioController {

    @Autowired UsuarioServiceI servicioUsuario;
    @Autowired PersonaServiceI servicioPersona;
    @Autowired EmpleadoServiceI servicioEmpleado;

    //Metodo que realiza el inicio de sesion, recogiendo los parametros 'username' y 'password' y comprobandolos con la api.
    //si el inicio no es satisfactorio, el usuario devuelto será null.


    @GetMapping("/login")
    public ResponseEntity<Usuario> logIn(@RequestParam("username") String username, @RequestParam("password") String password){

        ResponseEntity<Usuario> resp;
        Usuario u = new Usuario();

        try {

            Optional<Usuario> user = servicioUsuario.buscarPorUsername(username);
            HttpStatus htts = HttpStatus.NOT_FOUND;
            if (servicioUsuario.comprobarInicioSesion(username, getMD5(password))) {
                htts = HttpStatus.OK;
                System.out.println("Log in success");
                u= user.get();
                u.setToken(getJWTToken(username));
            }

            resp = new ResponseEntity<>(u, htts);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(u, HttpStatus.NOT_FOUND);

        }

        return resp;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    @GetMapping("/pubsLogin")
    public ResponseEntity<Usuario> logIn2(@RequestParam("user") String user, @RequestParam("password") String password){

        ResponseEntity<Usuario> resp;

        try {

            Optional<Usuario> usuario = servicioUsuario.login(user, getMD5(password));
            HttpStatus htts = HttpStatus.NOT_FOUND;
            Usuario u = new Usuario();
            if (usuario.isPresent()){
                u = usuario.get();
                htts = HttpStatus.OK;
            }else htts = HttpStatus.BAD_REQUEST;

            System.out.println(u);

            resp = new ResponseEntity<>(u, htts);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(new Usuario(), HttpStatus.NOT_FOUND);

        }

        return resp;
    }

    //Metodo que realiza el registro de nuevos usuarios.
    //recogiendo los parametros 'username' y 'password' y comprobandolos con la api.
    @PostMapping("/clientsignup")
    public ResponseEntity<Usuario> clientSignUp(@RequestBody Usuario user){

        ResponseEntity<Usuario> resp;

        try {

            Optional<Usuario> newUser = servicioUsuario.buscarPorUsername(user.getUsername());
            System.out.println(user);
            Optional<Persona> personaOp = servicioPersona.buscarPorDni(user.getPerson().getDni());

            Persona persona = new Persona();
            HttpStatus htts = HttpStatus.NOT_FOUND;

            Direccion direccion = Direccion.builder()
                    .calle(user.getDireccion().getCalle())
                    .numero(user.getDireccion().getNumero())
                    .piso(user.getDireccion().getPiso())
                    .puerta(user.getDireccion().getPuerta())
                    .portal(user.getDireccion().getPortal())
                    .build();

            if (newUser.isEmpty()) {
                htts = HttpStatus.OK;
                if (personaOp.isEmpty()){
                    persona = Persona.builder()
                            .dni(user.getPerson().getDni())
                            .name(user.getPerson().getName())
                            .secondName1(user.getPerson().getSecondName1())
                            .secondName2(user.getPerson().getSecondName2())
                            .email(user.getPerson().getEmail())
                            .build();
                }else persona = personaOp.get();

                Usuario u = Usuario.builder()
                        .username(user.getUsername())
                        .orders(user.getOrders())
                        .password(getMD5(user.getPassword()))
                        .rango(user.getRango())
                        .direccion(direccion)
                        .person(persona)
                        .build();

                servicioUsuario.insertar(user);

                System.out.println("Sign up success");
            }

            resp = new ResponseEntity<>(user, htts);

        }catch(Exception e){

           e.printStackTrace();
            resp = new ResponseEntity<>(user, HttpStatus.NOT_FOUND);

        }


        return resp;
    }

    //registro desde android
    @PostMapping("/pubsRegister")
    public ResponseEntity<Usuario> clientSignUpAndroid(@RequestBody UsuarioSerilize user){

        ResponseEntity<Usuario> resp;
        Usuario u = new Usuario();

        try {

            Optional<Usuario> newUser = servicioUsuario.buscarPorUsername(user.getUsername());

            Optional<Persona> personaOp = servicioPersona.buscarPorDni(user.getPerson().getDni());
            Persona persona = new Persona();
            HttpStatus htts = HttpStatus.NOT_FOUND;

            Direccion direccion = Direccion.builder()
                    .calle(user.getDireccion().getCalle())
                    .numero(user.getDireccion().getNumero())
                    .piso(user.getDireccion().getPiso())
                    .puerta(user.getDireccion().getPuerta())
                    .portal(user.getDireccion().getPortal())
                    .build();

            if (newUser.isEmpty()) {
                htts = HttpStatus.OK;
                if (personaOp.isEmpty()){
                    persona = Persona.builder()
                            .dni(user.getPerson().getDni())
                            .name(user.getPerson().getName())
                            .secondName1(user.getPerson().getSecondName1())
                            .secondName2(user.getPerson().getSecondName2())
                            .email(user.getPerson().getEmail())
                            .build();
                }else persona = personaOp.get();

                u = Usuario.builder()
                        .username(user.getUsername())
                        .orders(user.getOrders())
                        .password(getMD5(user.getPassword()))
                        .rango(user.getRango())
                        .direccion(user.getDireccion())
                        .person(persona)
                        .build();

                System.out.println(u);

                servicioUsuario.insertar(u);

                System.out.println("Sign up success");
            }

            resp = new ResponseEntity<>(u, htts);

        }catch(Exception e){

           e.printStackTrace();
            resp = new ResponseEntity<>(u, HttpStatus.NOT_FOUND);

        }


        return resp;
    }
    
    @GetMapping("/getUser")
    public ResponseEntity<Usuario> getUser(@RequestParam("dni") String dni){

        ResponseEntity<Usuario> resp;

        try {

        	Persona persona = servicioPersona.buscarPorDni(dni).orElse(Persona.builder().id(0).build());
        	
            List<Usuario> usuario = servicioUsuario.buscarPorPerson(persona);
            HttpStatus htts = HttpStatus.NOT_FOUND;
            Usuario u = new Usuario();
            if (!usuario.isEmpty()){
                u = usuario.get(0);
                htts = HttpStatus.OK;
            }else htts = HttpStatus.BAD_REQUEST;

            System.out.println(u);

            resp = new ResponseEntity<>(u, htts);

        }catch(Exception e){

            e.printStackTrace();
            resp = new ResponseEntity<>(new Usuario(), HttpStatus.NOT_FOUND);

        }

        return resp;
    }

    //método para encriptar las contraseñas
    public static String getMD5(String data) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        messageDigest.update(data.getBytes());
        byte[] digest = messageDigest.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(Integer.toHexString((int) (b & 0xff)));
        }
        return sb.toString();
    }
}
