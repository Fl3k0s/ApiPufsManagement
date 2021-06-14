package com.indytek.pufsmanagement.servicei;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.indytek.pufsmanagement.model.Cargo;
import com.indytek.pufsmanagement.model.Empleado;
import org.springframework.web.bind.annotation.RequestParam;

/*
interfaz del servicio de empleados

la clase EmpleadoService debe implementar esta interfaz y escribir sus metodos.
 */
public interface EmpleadoServiceI {
	public List<Empleado> listarEmpleadoPorPosicion(Cargo cargo);
	public List<Empleado> buscarTodos();

	Map<String, Integer> recogerInfoHoras(String desde, String hasta);
}
