package com.roy.service;

import java.util.List;

import com.roy.model.Empleado;

public interface IEmpleadosService {
	List<Empleado> buscarTodas();
	Empleado buscarPorId(Integer empNo);
	void guardar(Empleado empleado);
}
