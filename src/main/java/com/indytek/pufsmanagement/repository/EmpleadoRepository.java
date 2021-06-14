package com.indytek.pufsmanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indytek.pufsmanagement.model.Cargo;
import com.indytek.pufsmanagement.model.Empleado;

//repositorio de empleados
@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {
	public List<Empleado> findByPosition(Cargo cargo);
	public List<Empleado> findAll();

}
