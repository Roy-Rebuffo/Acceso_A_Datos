package com.example.demo.service;

import com.example.demo.model.Jornada;
import com.example.demo.repository.JornadaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaServiceImp implements IJornadaService {

    @Autowired
    private JornadaRepository repo;

    @Override
    public void guardar(Jornada jornada) {
        repo.save(jornada);
    }

    @Override
    public List<Jornada> buscarTodas() {
        return repo.findAll();
    }

    @Override
    public Jornada buscarPorId(Integer idJornada) {
        return repo.findById(idJornada).orElse(null);
    }

    @Override
    public void eliminar(Integer idJornada) {
        repo.deleteById(idJornada);
    }

    @Override
    public Page<Jornada> buscarTodas(Pageable page) {
        return repo.findAll(page);
    }

    @Override
    public List<Jornada> buscarByExample(Example<Jornada> example) {
        return List.of();
    }

    @Override
    public List<Jornada> buscarDestacadas() {
        // TODO Auto-generated method stub
        return null;
    }
}
