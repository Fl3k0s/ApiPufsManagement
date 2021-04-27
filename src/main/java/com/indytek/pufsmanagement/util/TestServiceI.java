package com.indytek.pufsmanagement.util;

import com.indytek.pufsmanagement.model.Usuario;

import java.util.Optional;

/*
interfaz del servicio de test

la clase TestService debe implementar esta interfaz y escribir sus metodos.
 */
public interface TestServiceI {

    public void insertar (Test test);
    public boolean borrar (int id);
    public boolean actualizar (Test test);
    public Optional<Test> buscarPorName (String name);

}
