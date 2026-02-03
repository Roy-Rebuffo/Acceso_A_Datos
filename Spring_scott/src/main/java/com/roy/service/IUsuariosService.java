package com.roy.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.roy.model.Usuario;

public interface IUsuariosService {
	List<Usuario> buscarTodas();
	Usuario buscarPorNumero(Integer userId);
	void guardar(Usuario usuario);
	
	void eliminar(Integer userId);
	Page<Usuario> buscarTodas(Pageable page);
	Usuario buscarPorUsername(String username);
}
