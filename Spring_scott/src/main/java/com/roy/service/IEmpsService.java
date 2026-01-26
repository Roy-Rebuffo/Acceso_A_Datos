package com.roy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.roy.model.Emp;

public interface IEmpsService {
	List<Emp> buscarTodas();
	Emp buscarPorNumero(Integer empNo);
	void guardar(Emp empleado);
	
	void eliminar(Integer empNo);
	Page<Emp> buscarTodas(Pageable page);
}
