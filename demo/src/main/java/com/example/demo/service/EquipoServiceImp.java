package com.example.demo.service;

import com.example.demo.model.Equipo;
import com.example.demo.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoServiceImp implements IEquipoService{

    @Autowired
    private EquipoRepository repo;

    @Override
    public void guardar(Equipo equipo){
        repo.save(equipo);
    }

    @Override
    public List<Equipo> buscarTodas(){
        return repo.findAll();
    }
    @Override
    public Equipo buscarPorId(Integer idEquipo) {
        return repo.findById(idEquipo).orElse(null);
    }

    @Override
    public void eliminar(Integer idEquipo) {
        repo.deleteById(idEquipo);
    }

    @Override
    public Page<Equipo> buscarTodas(Pageable page) {
        return repo.findAll(page);
    }

    @Override
    public List<Equipo> buscarByExample(Example<Equipo> example) {
        return repo.findAll(example);
    }
}
