package com.roy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.roy.model.Perfile;

public interface IPerfilesService {
	List<Perfile> buscarTodas();
	Perfile buscarPorId(Integer perfilId);
	void guardar(Perfile perfil);
	
	void eliminar(Integer perfilId);
	Page<Perfile> buscarTodas(Pageable page);
}
