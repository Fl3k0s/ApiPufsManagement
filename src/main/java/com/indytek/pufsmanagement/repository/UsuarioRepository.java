package com.indytek.pufsmanagement.repository;

import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
/*
repositorio de usuarios
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    //Optional<Usuario> findById (int id);
    Optional<Usuario> findByUsername (String username);
    Optional<Usuario> findByPassword (String password);

}
