package com.indytek.pufsmanagement.servicei;

import java.util.List;

import com.indytek.pufsmanagement.model.Cargo;
import com.indytek.pufsmanagement.model.Empleado;

/*
interfaz del servicio de empleados

la clase EmpleadoService debe implementar esta interfaz y escribir sus metodos.
 */
public interface EmpleadoServiceI {
	public List<Empleado> listarEmpleadoPorPosicion(Cargo cargo);

}
