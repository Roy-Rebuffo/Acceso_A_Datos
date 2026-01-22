package com.roy.service;

import java.util.List;

import com.roy.model.Departamento;

public interface IDepartamentosService {
	List<Departamento> buscarTodas();
	Departamento buscarPorNumero(Integer deptNo);
	void guardar(Departamento departamento);
}
