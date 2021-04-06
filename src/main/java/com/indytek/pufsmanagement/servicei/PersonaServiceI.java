package com.indytek.pufsmanagement.servicei;

import java.util.Optional;

import com.indytek.pufsmanagement.model.Persona;

public interface PersonaServiceI {

	public void insertar (Persona  persona);
	public boolean borrar (String username);
	public boolean actualizar (Persona persona);
	public Optional<Persona> buscarPorUsername (String username);
	
}
