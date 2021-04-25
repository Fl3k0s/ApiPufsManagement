package com.indytek.pufsmanagement.util;

import com.indytek.pufsmanagement.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService implements TestServiceI {

    @Autowired private TestRepository testRepo;

    @Override
    public void insertar(Test test) {

        System.out.println("objeto testx dentro del servicio -> " + test.toString() + " <-");

        testRepo.save(test);

    }

    @Override
    public boolean borrar(int id) {
        boolean x  = false;

        if(testRepo.findById(id).isPresent()) {
            testRepo.deleteById(id);
            x = true;
        }

        return x;
    }

    @Override
    public boolean actualizar(Test test) {
        return false;
    }

    @Override
    public Optional<Test> buscarPorName(String name) {

        return testRepo.findByName(name);
    }


}
