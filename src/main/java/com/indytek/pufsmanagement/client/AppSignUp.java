package com.indytek.pufsmanagement.client;

import com.indytek.pufsmanagement.model.Rango;
import com.indytek.pufsmanagement.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.HashSet;

/*
Esta clase se encarga de probar el registro de un nuevo usuario, envia al metodo get del controlador de usuario y devuelve ese usuario.

 */
public class AppSignUp {

    public static void main(String[] args) {

        registrarse("1234","1234");

    }

    public static void registrarse (String username, String password)
    {
        final String URL = "http://localhost:8080/pufs/users/clientsignup";
        RestTemplate restTemplate = new RestTemplate();

        try
        {

            //el cliente se registra sin definir la direccion, pero no podra realizar pedidos hasta que la defina dentro de la app.

            Usuario newUser = Usuario.builder()
                    .username(username)
                    .password(password)
                    .rango(Rango.BRONCE)
                    .orders(new HashSet<>())
                    .build();

            Usuario response  = restTemplate.postForObject(URL, newUser, Usuario.class);

            System.out.println(response);
        }

        catch(HttpClientErrorException e)
        {
            System.out.println ("error");
        }
    }

}
