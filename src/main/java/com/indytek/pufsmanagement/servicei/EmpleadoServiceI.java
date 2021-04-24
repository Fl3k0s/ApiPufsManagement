package com.indytek.pufsmanagement.servicei;

import com.indytek.pufsmanagement.model.Empleado;
import com.indytek.pufsmanagement.model.Persona;

import java.util.Optional;

public interface EmpleadoServiceI {

	public void insertar (Empleado  persona);
	public boolean borrar (String username);
	public boolean actualizar (Empleado persona);
	public Optional<Empleado> buscarPorName (String name);
	
}
