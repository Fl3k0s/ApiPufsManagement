package com.indytek.pufsmanagement.client;

import com.indytek.pufsmanagement.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class AppLogIn {

    public static void main(String[] args) {

        iniciarSesion("admin","admin");

    }

    public static void iniciarSesion (String username, String password)
    {
        final String URL = "http://localhost:8080/pufs/users/login?username={username}&password={password}";
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
