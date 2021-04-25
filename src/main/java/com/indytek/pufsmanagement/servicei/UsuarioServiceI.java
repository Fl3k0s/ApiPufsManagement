package com.indytek.pufsmanagement.servicei;

import com.indytek.pufsmanagement.model.Usuario;

import java.util.Optional;

public interface UsuarioServiceI {

    public void insertar (Usuario usuario);
    public boolean borrar (int id);
    public boolean actualizar (Usuario usuario);
    public Optional<Usuario> buscarPorUsername (String username);
    public Optional<Usuario> buscarPorPassword (String password);

    public boolean comprobarInicioSesion (String username, String password);

}