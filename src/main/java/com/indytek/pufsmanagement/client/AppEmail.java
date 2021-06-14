package com.indytek.pufsmanagement.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
/*
Esta clase se encarga de probar el envío de emails.

 */
public class AppEmail {

    public static void main(String[] args) {



        //enviar("oscar.del@educa.madrid.org","hola mundo","hello world");
/*
        Map<String, Integer> info =
                infoHorasEmpleados(
                        LocalDateTime.of(LocalDate.of(2021,01,01), LocalTime.of(01,00)),
                        LocalDateTime.now());
*/
        infoHorasEmpleados("2021-01-01","2021-06-14");

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

    public static void infoHorasEmpleados (String desde, String hasta)
    {
        final String URL = "http://localhost:8080/pufs/people/getemployeehours";
        RestTemplate restTemplate = new RestTemplate();

        try
        {
            //funcionará sin especificar el tipo?
            ResponseEntity<Map> response  = restTemplate.getForEntity(URL, Map.class, desde, hasta);

            Map<String, Integer> mapa = response.getBody();

            mapa.forEach((k,v) -> System.out.print(k + " -> " + v));

        }

        catch(HttpClientErrorException e)
        {
            System.out.println ("error");
        }



    }

}
