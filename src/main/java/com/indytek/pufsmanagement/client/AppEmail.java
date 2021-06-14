package com.indytek.pufsmanagement.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
/*
Esta clase se encarga de probar el envío de emails.

 */
public class AppEmail {

    public static void main(String[] args) {



        enviar("oscar.del@educa.madrid.org","hola mundo","hello world");

    }

    //envio de email
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
            ResponseEntity<String> response  = restTemplate.postForEntity(URL, params,String.class);

            System.out.println(response.getBody());
        }

        catch(HttpClientErrorException e)
        {
            System.out.println ("error");
        }
    }

    public static void infoHorasEmpleados (LocalDateTime desde, LocalDateTime hasta)
    {
        final String URL = "http://localhost:8080/pufs/people/getemployeehours";
        RestTemplate restTemplate = new RestTemplate();

        try
        {
            //funcionará sin especificar el tipo?
            ResponseEntity<Map> response  = restTemplate.getForEntity(URL, Map.class, desde, hasta);

            System.out.println(response.getBody());
        }

        catch(HttpClientErrorException e)
        {
            System.out.println ("error");
        }
    }

}
