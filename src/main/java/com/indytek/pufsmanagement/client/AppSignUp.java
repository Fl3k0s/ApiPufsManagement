package com.indytek.pufsmanagement.client;

import com.indytek.pufsmanagement.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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
            ResponseEntity<Usuario> response  = restTemplate.getForEntity(URL, Usuario.class);

            System.out.println(response.getBody());
        }

        catch(HttpClientErrorException e)
        {
            System.out.println ("error");
        }
    }

}
