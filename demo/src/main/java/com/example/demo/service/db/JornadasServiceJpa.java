package com.example.demo.service.db;

import com.example.demo.model.Jornada;
import com.example.demo.repository.JornadaRepository;
import com.example.demo.service.IJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JornadasServiceJpa implements IJornadaService {

    @Autowired
    private JornadaRepository jornadaRepository;

    @Override
    public void guardar(Jornada jornada) {
        jornadaRepository.save(jornada);

    }

    @Override
    public List<Jornada> buscarTodas() {
        return jornadaRepository.findAll();
    }

    @Override
    public Jornada buscarPorId(Integer idJornada) {
        Optional<Jornada> optional = jornadaRepository.findById(idJornada);
        if(optional.isPresent()) return optional.get();
        return null;
    }

    @Override
    public List<Jornada> buscarDestacadas() {
        return List.of();
    }

    @Override
    public void eliminar(Integer idJornada) {
        jornadaRepository.deleteById(idJornada);

    }

    @Override
    public Page<Jornada> buscarTodas(Pageable page) {
        return jornadaRepository.findAll(page);
    }

    @Override
    public List<Jornada> buscarByExample(Example<Jornada> example) {
        // TODO Auto-generated method stub
        return null;
    }

}
