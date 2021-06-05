package com.indytek.pufsmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indytek.pufsmanagement.model.Cargo;
import com.indytek.pufsmanagement.model.Empleado;
import com.indytek.pufsmanagement.repository.EmpleadoRepository;
import com.indytek.pufsmanagement.servicei.EmpleadoServiceI;
@Service
public class EmpleadoService implements EmpleadoServiceI {
	@Autowired private EmpleadoRepository empleadoRepo;

	@Override
	public List<Empleado> listarEmpleadoPorPosicion(Cargo cargo) {
		// TODO Auto-generated method stub
		return empleadoRepo.findByPosition(cargo);
	}

}
