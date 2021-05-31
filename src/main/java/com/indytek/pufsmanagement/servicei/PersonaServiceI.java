package com.indytek.pufsmanagement.servicei;

import java.util.Optional;

import com.indytek.pufsmanagement.model.Persona;
/*
interfaz del servicio de personas

la clase PersonaService debe implementar esta interfaz y escribir sus metodos.
 */
public interface PersonaServiceI {

	public void insertar (Persona  persona);
	public boolean borrar (String username);
	public boolean actualizar (Persona persona);
	public Optional<Persona> buscarPorName (String name);
	public Optional<Persona> buscarPorEmail (String email);
	
}
