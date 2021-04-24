package com.indytek.pufsmanagement.service;

import com.indytek.pufsmanagement.model.Cliente;
import com.indytek.pufsmanagement.model.Persona;
import com.indytek.pufsmanagement.repository.ClienteRepository;
import com.indytek.pufsmanagement.repository.PersonaRepository;
import com.indytek.pufsmanagement.servicei.ClienteServiceI;
import com.indytek.pufsmanagement.servicei.PersonaServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements ClienteServiceI {

	@Autowired private ClienteRepository clienteRepo;

	@Override
	public void insertar(Cliente cliente) {

		clienteRepo.save(cliente);
		
	}

	@Override
	public boolean borrar(String name) {
		boolean x  = false;
		
		if(buscarPorName(name).isPresent()) {
			clienteRepo.deleteById(name);
			x = true;
		}

		return x;
	}

	@Override
	public boolean actualizar(Cliente cliente) {
		

		return false;
	}

	@Override
	public Optional<Cliente> buscarPorName(String name) {
		

		return clienteRepo.findByName(name);
	}
	
}
