package com.indytek.pufsmanagement.servicei;

import com.indytek.pufsmanagement.model.Usuario;

import java.util.Optional;
/*
interfaz del servicio de usuario

la clase UsuarioService debe implementar esta interfaz y escribir sus metodos.
 */
public interface UsuarioServiceI {

    public void insertar (Usuario usuario);
    public boolean borrar (int id);
    public boolean actualizar (Usuario usuario);
    public Optional<Usuario> buscarPorUsername (String username);
    public Optional<Usuario> buscarPorPassword (String password);
    public Optional<Usuario> login(String user, String pass);

    public boolean comprobarInicioSesion (String username, String password);

}
