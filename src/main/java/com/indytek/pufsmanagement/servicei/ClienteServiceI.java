package com.indytek.pufsmanagement.servicei;

import com.indytek.pufsmanagement.model.Cliente;
import com.indytek.pufsmanagement.model.Persona;

import java.util.Optional;

public interface ClienteServiceI {

	public void insertar (Cliente cliente);
	public boolean borrar (String username);
	public boolean actualizar (Cliente cliente);
	public Optional<Cliente> buscarPorName (String name);
	
}
