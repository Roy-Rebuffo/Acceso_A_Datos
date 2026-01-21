package com.roy.service;

import java.util.List;

import com.roy.demo.model.Vacante;

public interface iVacantesService {
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Integer idVacante);
	List<Vacante> buscarPorAño(Integer año);
	boolean borrarPorId(Integer idVacante);
}
