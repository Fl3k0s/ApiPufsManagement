package com.indytek.pufsmanagement.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class AppEmail {

    public static void main(String[] args) {



        enviar("email@gmail.com","titulo","mensaje");

    }

    public static void enviar (String destino, String titulo, String mensaje)
    {
        final String URL = "http://localhost:8080/pufs/test/sendemail";
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("destino",destino);
        params.put("titulo",titulo);
        params.put("mensaje",mensaje);

        try
        {
            ResponseEntity<String> response  = restTemplate.getForEntity(URL, String.class, params);

            System.out.println(response.getBody());
        }

        catch(HttpClientErrorException e)
        {
            System.out.println ("error");
        }
    }

}
