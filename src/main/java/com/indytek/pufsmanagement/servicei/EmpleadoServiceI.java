package com.indytek.pufsmanagement.servicei;

import java.util.List;

import com.indytek.pufsmanagement.model.Cargo;
import com.indytek.pufsmanagement.model.Empleado;

public interface EmpleadoServiceI {
	public List<Empleado> listarEmpleadoPorPosicion(Cargo cargo);

}
