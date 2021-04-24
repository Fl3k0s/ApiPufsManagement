package com.indytek.pufsmanagement.service;

import com.indytek.pufsmanagement.model.Empleado;
import com.indytek.pufsmanagement.model.Persona;
import com.indytek.pufsmanagement.repository.EmpleadoRepository;
import com.indytek.pufsmanagement.repository.PersonaRepository;
import com.indytek.pufsmanagement.servicei.EmpleadoServiceI;
import com.indytek.pufsmanagement.servicei.PersonaServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoService implements EmpleadoServiceI {

	@Autowired private EmpleadoRepository empleadoRepo;

	@Override
	public void insertar(Empleado empleado) {

		empleadoRepo.save(empleado);
		
	}

	@Override
	public boolean borrar(String name) {
		boolean x  = false;
		
		if(buscarPorName(name).isPresent()) {
			empleadoRepo.deleteById(name);
			x = true;
		}

		return x;
	}

	@Override
	public boolean actualizar(Empleado empleado) {
		

		return false;
	}

	@Override
	public Optional<Empleado> buscarPorName(String name) {
		

		return empleadoRepo.findByName(name);
	}
	
}
