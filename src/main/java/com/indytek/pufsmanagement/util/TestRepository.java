package com.indytek.pufsmanagement.util;

import com.indytek.pufsmanagement.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/*
repositorio de pruebas
 */
public interface TestRepository extends CrudRepository<Test, Integer> {

    Optional<Test> findByName (String name);

}
