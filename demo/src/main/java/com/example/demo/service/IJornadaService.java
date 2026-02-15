package com.example.demo.service;

import com.example.demo.model.Jornada;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IJornadaService {
    void guardar(Jornada jornada);
    List<Jornada> buscarTodas();
    Jornada buscarPorId(Integer idJornada);
    List<Jornada> buscarDestacadas();
    void eliminar(Integer idJornada);
    Page<Jornada> buscarTodas(Pageable page);

    List<Jornada> buscarByExample(Example<Jornada> example);
}
