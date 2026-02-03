package com.roy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roy.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer>{
	// Spring genera: SELECT * FROM Usuarios WHERE username = ?
    Usuario findByUsername(String username);
}
