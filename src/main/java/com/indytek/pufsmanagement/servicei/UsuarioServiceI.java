package com.indytek.pufsmanagement.servicei;

import com.indytek.pufsmanagement.model.Pedido;
import com.indytek.pufsmanagement.model.Persona;
import com.indytek.pufsmanagement.model.Usuario;

import java.util.List;
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
    public List<Usuario> buscarPorPerson (Persona person);
    public Optional<Usuario> login(String user, String pass);

    public boolean comprobarInicioSesion (String username, String password);

    public List<Pedido> todosLosPedidosDeUnUser(String user);

    public Usuario quitarPedido(String user, int id);

}
