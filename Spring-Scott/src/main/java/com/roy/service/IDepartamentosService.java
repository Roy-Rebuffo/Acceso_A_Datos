package com.roy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.roy.model.Dept;

public interface IDepartamentosService {
	List<Dept> buscarTodas();
	Dept buscarPorNumero(Integer deptNo);
	void guardar(Dept departamento);
	
	void eliminar(Integer deptNo);
	Page<Dept> buscarTodas(Pageable page);
}
