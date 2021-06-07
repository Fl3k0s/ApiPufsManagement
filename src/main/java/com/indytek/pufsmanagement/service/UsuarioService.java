package com.indytek.pufsmanagement.service;

import com.indytek.pufsmanagement.model.Pedido;
import com.indytek.pufsmanagement.model.Usuario;
import com.indytek.pufsmanagement.repository.UsuarioRepository;
import com.indytek.pufsmanagement.servicei.UsuarioServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
/*
servicio de usuarios

los metodos que son llamados desde el controlador de usuarios se encuentran aqui
 */
public class UsuarioService  implements UsuarioServiceI {

    @Autowired UsuarioRepository usuarioRepo;

    @Override
    public void insertar(Usuario usuario) {

        usuarioRepo.save(usuario);

    }

    @Override
    public boolean borrar(int id) {
        boolean x  = false;

        if(usuarioRepo.findById(id).isPresent()) {
            usuarioRepo.deleteById(id);
            x = true;
        }

        return x;
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        boolean x = false;

        usuarioRepo.save(usuario);

        return x;
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {

        return usuarioRepo.findByUsername(username);
    }

    @Override
    public Optional<Usuario> buscarPorPassword(String password) {

        return usuarioRepo.findByPassword(password);
    }

    @Override
    public Optional<Usuario> login(String user, String pass) {
        return usuarioRepo.buscarPorUserOMailConPass(user, pass);
    }

    //comprueba el inicio de seseión
    @Override
    public boolean comprobarInicioSesion(String username, String password) {
        boolean x = false;
        Optional<Usuario> u = buscarPorUsername(username);

        if(u.isPresent()){

            if(u.get().getPassword().equalsIgnoreCase(password))
                x = true;

        }

        return x;
    }

    @Override
    public List<Pedido> todosLosPedidosDeUnUser(String user) {
        return usuarioRepo.buscarTodosLosPedidosDeUnUser(user);
    }

    //Recibe el username y el id del pedido a eliminar del usuario.
    @Override
    public Usuario quitarPedido(String user, int id) {
        int size;
        boolean delete = false;
        Usuario u = usuarioRepo.findByUsername(user).get();
        size = u.getOrders().size();
        System.out.println(u);

        u.getOrders().removeIf(p ->p.getId() == id);

        System.out.println(u);
        if (u.getOrders().size() != size)
            System.out.println("deleted");
        return u;
    }


}
