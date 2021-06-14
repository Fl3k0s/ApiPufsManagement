package com.indytek.pufsmanagement.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

				System.out.println("Horas entrantes: Desde " + sDesde + " -- Hasta " + sHasta);

		EmpleadoHoras infoHoras = EmpleadoHoras.builder().build();

			List<Empleado> empleados = buscarTodos();
			List<EmpleadoHoras> lista = new ArrayList<>();

			Empleado empleado;

			//devolver bien el empleado horas construido xaxi

			LocalDate desde = LocalDate.parse(sDesde);
			LocalDate hasta = LocalDate.parse(sHasta);

			int numDias = hasta.getDayOfYear() - desde.getDayOfYear();
			int numMinutos = 0;

			for (int i = 0; i < empleados.size(); i ++){

				//calculando empleados uno por uno
				empleado = empleados.get(i);

				LocalTime entrada = empleado.getHoraEntrada();
				LocalTime salida = empleado.getHoraSalida();


				if(salida.isBefore(entrada)){
					//17:00 -> 2:00 (9 h, 540 m, 32.400 s) (63 horas raquel)
					/*

							ARREGLAR

					//Calculado en dos partes, hasta las 23:59 y desde las 00:00 NO FUNCIONA
					Duration durationPart1 = Duration.between(entrada, LocalTime.of(23,59));

					Long numMinutosLongPart1 = durationPart1.getSeconds() + 60;


					Duration durationPart2 = Duration.between(LocalTime.of(0,0), salida);

					Long numMinutosLongPart2 = durationPart1.getSeconds();

					//+1 por que hay que compensar el minuto entre las 23:59 y las 00:00
					numMinutos = numMinutosLongPart1.intValue() + numMinutosLongPart2.intValue();
					*/
				}
				else{

					//10:00 -> 18:00 facil
					Duration duration = Duration.between(entrada, salida);

					Long numMinutosLong = duration.getSeconds();

					numMinutos = numMinutosLong.intValue();

				}

				System.out.println("Empleado: " + empleado.getName() + " -> Minutos: " + numMinutos / 60 + " Segundos: " + numMinutos + "/n");

				numMinutos = numMinutos / 60;




				lista.add(EmpleadoHoras.builder()
						.nameSurname(empleado.getName() + " " + empleado.getSecondName1())
						.horas((numMinutos * numDias)/60).build());

			}

		return lista;
	}

}
