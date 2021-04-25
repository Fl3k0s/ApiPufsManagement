package com.indytek.pufsmanagement.service;

import com.indytek.pufsmanagement.model.Usuario;
import com.indytek.pufsmanagement.repository.UsuarioRepository;
import com.indytek.pufsmanagement.servicei.UsuarioServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService  implements UsuarioServiceI {

    @Autowired private UsuarioRepository usuarioRepo;

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
        return false;
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
    public boolean comprobarInicioSesion(String username, String password) {
        boolean x = false;
        Optional<Usuario> u = buscarPorUsername(username);

        if(u.isPresent()){

            if(u.get().getPassword().equalsIgnoreCase(password))
                x = true;

        }

        return x;
    }


}
