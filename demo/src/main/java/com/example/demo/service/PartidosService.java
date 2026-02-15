package com.example.demo.service;

import com.example.demo.dto.PartidoDTO;
import com.example.demo.dto.TablaDTO;
import com.example.demo.model.Equipo;
import com.example.demo.model.Partido;
import com.example.demo.repository.EquipoRepository;
import com.example.demo.repository.PartidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PartidosService {

    @Autowired
    private PartidosRepository repo;

    @Autowired
    private EquipoRepository repoEquipos;

    public List<PartidoDTO> buscarPartidosDTO(Integer idJornada){

        List<Partido> partidos = repo.findById_Idjornada(idJornada);
        List<PartidoDTO> lista = new ArrayList<>();

        for (Partido p : partidos) {

            PartidoDTO dto = new PartidoDTO();

            Equipo local = repoEquipos.findById(p.getId().getIdlocal()).orElse(null);
            Equipo visitante = repoEquipos.findById(p.getId().getIdvisitante()).orElse(null);

            dto.setLocalNombre(local.getNombre());
            dto.setVisitanteNombre(visitante.getNombre());
            dto.setLocalLogo(local.getLogo());
            dto.setVisitanteLogo(visitante.getLogo());
            dto.setGolesLocal(p.getGolLocal());
            dto.setGolesVisitante(p.getGolVisitante());

            lista.add(dto);
        }

        return lista;
    }

    public List<TablaDTO> calcularTablaGeneral(int jornadaMaxima) {

        List<Partido> partidos = repo.findById_IdjornadaLessThanEqual(jornadaMaxima);

        Map<Integer, TablaDTO> tabla = new HashMap<>();

        for (Partido p : partidos) {

            int idLocal = p.getId().getIdlocal();
            int idVisitante = p.getId().getIdvisitante();

            Equipo local = repoEquipos.findById(idLocal).orElse(null);
            Equipo visitante = repoEquipos.findById(idVisitante).orElse(null);

            tabla.putIfAbsent(idLocal, crearFila(local));
            tabla.putIfAbsent(idVisitante, crearFila(visitante));

            TablaDTO tLocal = tabla.get(idLocal);
            TablaDTO tVisitante = tabla.get(idVisitante);

            int gl = p.getGolLocal();
            int gv = p.getGolVisitante();

            // PJ
            tLocal.setPj(tLocal.getPj() + 1);
            tVisitante.setPj(tVisitante.getPj() + 1);

            // Goles
            tLocal.setGf(tLocal.getGf() + gl);
            tLocal.setGc(tLocal.getGc() + gv);

            tVisitante.setGf(tVisitante.getGf() + gv);
            tVisitante.setGc(tVisitante.getGc() + gl);

            // Diferencia
            tLocal.setDg(tLocal.getGf() - tLocal.getGc());
            tVisitante.setDg(tVisitante.getGf() - tVisitante.getGc());

            // Puntos
            if (gl > gv) {
                tLocal.setPg(tLocal.getPg() + 1);
                tLocal.setPts(tLocal.getPts() + 3);
                tVisitante.setPp(tVisitante.getPp() + 1);

            } else if (gl < gv) {
                tVisitante.setPg(tVisitante.getPg() + 1);
                tVisitante.setPts(tVisitante.getPts() + 3);
                tLocal.setPp(tLocal.getPp() + 1);

            } else {
                tLocal.setPe(tLocal.getPe() + 1);
                tVisitante.setPe(tVisitante.getPe() + 1);
                tLocal.setPts(tLocal.getPts() + 1);
                tVisitante.setPts(tVisitante.getPts() + 1);
            }
        }

        return tabla.values().stream()
                .sorted(Comparator.comparing(TablaDTO::getPts).reversed()
                        .thenComparing(TablaDTO::getDg).reversed()
                        .thenComparing(TablaDTO::getGf).reversed())
                .toList();
    }

    private TablaDTO crearFila(Equipo e) {
        TablaDTO t = new TablaDTO();
        t.setIdequipo(e.getIdequipo());
        t.setNombre(e.getNombre());
        t.setLogo(e.getLogo());
        return t;
    }


}
