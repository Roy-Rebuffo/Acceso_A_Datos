package com.example.demo.repository;

import com.example.demo.model.Partido;
import com.example.demo.model.PartidoPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidosRepository extends JpaRepository<Partido, PartidoPK> {
    List<Partido> findById_Idjornada(int idjornada);
    List<Partido> findById_IdjornadaLessThanEqual(int idJornada);
}
