package com.indytek.pufsmanagement.service;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.indytek.pufsmanagement.model.EmpleadoHoras;
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
	public List<EmpleadoHoras> recogerInfoHoras(String sDesde, String sHasta) {

		EmpleadoHoras infoHoras = EmpleadoHoras.builder().build();

			List<Empleado> empleados = buscarTodos();
			List<EmpleadoHoras> lista = new ArrayList<>();

			Empleado empleado;

			LocalDate desde = LocalDate.parse(sDesde);
			LocalDate hasta = LocalDate.parse(sHasta);

			//se calculan los dias
			int numDias = hasta.getDayOfYear() - desde.getDayOfYear();
			int numMinutos = 0;

			for (int i = 0; i < empleados.size(); i ++){

				//calculando empleados uno por uno
				empleado = empleados.get(i);

				LocalTime entrada = empleado.getHoraEntrada();
				LocalTime salida = empleado.getHoraSalida();

				if(salida.isBefore(entrada)){
					//Calculado en dos partes
					Long durationPart1 = (Duration.between(entrada, LocalTime.of(23,00)).toMinutes());

					Long durationPart2 = Duration.between(LocalTime.of(0,0), salida).toMinutes();

					//+1 por que hay que compensar el minuto entre las 23:59 y las 00:00
					numMinutos = durationPart1.intValue() + durationPart2.intValue() + 60;
				}
				else{

					//10:00 -> 18:00 facil
					Long numMinutosLong = Duration.between(entrada, salida).toMinutes();

					numMinutos = numMinutosLong.intValue();

				}

				System.out.println("Empleado: " + empleado.getName() + " -> horas: " + numMinutos / 60 + " minutos: " + numMinutos);

				lista.add(EmpleadoHoras.builder()
						.nameSurname(empleado.getName() + " " + empleado.getSecondName1())
						.minutos(numMinutos * numDias).build());

			}

		return lista;
	}

}
