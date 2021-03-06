package com.indytek.pufsmanagement.repository;

import com.indytek.pufsmanagement.model.Pedido;
import com.indytek.pufsmanagement.model.Persona;
import com.indytek.pufsmanagement.model.PersonaSerialize;
import com.indytek.pufsmanagement.model.Producto;
import com.indytek.pufsmanagement.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/*
repositorio de usuarios
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    //Optional<Usuario> findById (int id);
    Optional<Usuario> findByUsername (String username);
    Optional<Usuario> findByPassword (String password);
    List<Usuario> findByPerson (Persona person);
    @Query("SELECT u FROM Usuario u WHERE u.username = ?1 OR u.person.email = ?1 AND u.password = ?2")
    Optional<Usuario> buscarPorUserOMailConPass(String user, String pass);
    @Query("SELECT u.orders FROM Usuario u WHERE u.username = ?1")
    List<Pedido> buscarTodosLosPedidosDeUnUser(String user);

}
