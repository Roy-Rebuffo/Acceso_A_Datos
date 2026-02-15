package com.example.demo.service;

import com.example.demo.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUsuariosService {
    void guardar(Usuario usuario);
    void eliminar(Integer idUsuario);
    List<Usuario> buscarTodos();
    List<Usuario> buscarRegistrados();
    Usuario buscarPorId(Integer idUsuario);
    Usuario buscarPorUsername(String username);
    int bloquear(int idUsuario);
    int activar(int idUsuario);
}
