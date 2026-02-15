package com.example.demo.service.db;


import com.example.demo.model.Equipo;
import com.example.demo.repository.EquipoRepository;
import com.example.demo.service.IEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquiposServiceJpa implements IEquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public void guardar(Equipo equipo) {
        equipoRepository.save(equipo);
    }

    @Override
    public List<Equipo> buscarTodas() {
        return equipoRepository.findAll();
    }

    @Override
    public Equipo buscarPorId(Integer idEquipo) {
        Optional<Equipo> optional = equipoRepository.findById(idEquipo);
        if(optional.isPresent()) return optional.get();
        return null;
    }

    @Override
    public void eliminar(Integer idEquipo) {
        equipoRepository.deleteById(idEquipo);

    }

    @Override
    public Page<Equipo> buscarTodas(Pageable page) {
        return equipoRepository.findAll(page);
    }

    @Override
    public List<Equipo> buscarByExample(Example<Equipo> example) {
        // TODO Auto-generated method stub
        return null;
    }

}
