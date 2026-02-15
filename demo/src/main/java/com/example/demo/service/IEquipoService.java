package com.example.demo.service;

import com.example.demo.model.Equipo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEquipoService {
    void guardar(Equipo equipo);
    List<Equipo> buscarTodas();
    Equipo buscarPorId(Integer idEquipo);
    void eliminar(Integer idEquipo);
    Page<Equipo> buscarTodas(Pageable page);
    List<Equipo> buscarByExample(Example<Equipo> example);
}
