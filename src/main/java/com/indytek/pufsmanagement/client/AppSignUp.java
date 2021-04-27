package com.indytek.pufsmanagement.client;

import com.indytek.pufsmanagement.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
/*
Esta clase se encarga de probar el registro de un nuevo usuario, envia al metodo get del controlador de usuario y devuelve ese usuario.

 */
public class AppSignUp {

    public static void main(String[] args) {

        registrarse("1234","1234");

    }

    public static void registrarse (String username, String password)
    {
        final String URL = "http://localhost:8080/pufs/users/clientsignup?username={username}&password={password}";
        RestTemplate restTemplate = new RestTemplate();

        try
        {

            HashMap<String,String> params = new HashMap<String,String>();
            params.put("username",username);
            params.put("password",password);

            ResponseEntity<Usuario> response  = restTemplate.getForEntity(URL, Usuario.class, params);

            System.out.println(response.getBody());
        }

        catch(HttpClientErrorException e)
        {
            System.out.println ("error");
        }
    }

}
