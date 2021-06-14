package com.indytek.pufsmanagement.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indytek.pufsmanagement.model.Cargo;
import com.indytek.pufsmanagement.model.Empleado;
import com.indytek.pufsmanagement.repository.EmpleadoRepository;
import com.indytek.pufsmanagement.servicei.EmpleadoServiceI;
import org.springframework.web.bind.annotation.RequestParam;

@Service
/*
servicio de empleados

los metodos que son llamados desde el controlador de persona se encuentran aqui
 */
public class EmpleadoService implements EmpleadoServiceI {
	@Autowired private EmpleadoRepository empleadoRepo;

	@Override
	public List<Empleado> listarEmpleadoPorPosicion(Cargo cargo) {
		// TODO Auto-generated method stub
		return empleadoRepo.findByPosition(cargo);
	}

	@Override
	public List<Empleado> buscarTodos() {
		return empleadoRepo.findAll();
	}

	@Override
	public Map<String, Float> recogerInfoHoras(LocalDateTime desde, LocalDateTime hasta) {

		Map<String, Float> infoHoras = new HashMap<String, Float>();

			List<Empleado> empleados = buscarTodos();
			Empleado empleado = Empleado.builder().id(0).build();

			for (int i = 0; i < empleados.size(); i ++){

				//calculando empleados uno por uno
				empleado = empleados.get(i);


				LocalTime entrada = empleado.getHoraEntrada();
				LocalTime salida = empleado.getHoraSalida();

			}

		return infoHoras;
	}

}
