package com.indytek.pufsmanagement.util;

import com.indytek.pufsmanagement.model.Usuario;

import java.util.Optional;

public interface TestServiceI {

    public void insertar (Test usuario);
    public boolean borrar (int id);
    public boolean actualizar (Test usuario);
    public Optional<Test> buscarPorName (String name);

}
