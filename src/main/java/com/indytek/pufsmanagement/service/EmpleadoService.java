package com.indytek.pufsmanagement.service;

import java.time.LocalDate;
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

import static java.time.temporal.ChronoUnit.MINUTES;

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
	public Map<String, Integer> recogerInfoHoras(LocalDate desde, LocalDate hasta) {

		Map<String, Integer> infoHoras = new HashMap<String, Integer>();

			List<Empleado> empleados = buscarTodos();
			Empleado empleado = Empleado.builder().id(0).build();

			int numDias = hasta.getDayOfYear() - desde.getDayOfYear();
			int numMinutos = 0;

			for (int i = 0; i < empleados.size(); i ++){

				//calculando empleados uno por uno
				empleado = empleados.get(i);

				LocalTime entrada = empleado.getHoraEntrada();
				LocalTime salida = empleado.getHoraSalida();

				if(salida.isBefore(entrada)){

					//17:30 -> 01:30
					Long longMinutos = MINUTES.between(desde,hasta);
					numMinutos = longMinutos.intValue();

				}
				else{

					//10:00 -> 18:00
					Long longMinutos = MINUTES.between(desde,hasta);
					numMinutos = longMinutos.intValue();

				}

			}

		return infoHoras;
	}

}
