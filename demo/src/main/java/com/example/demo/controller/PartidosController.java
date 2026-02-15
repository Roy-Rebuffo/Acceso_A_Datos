package com.example.demo.controller;


import com.example.demo.dto.PartidoDTO;
import com.example.demo.service.PartidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/partidos")
public class PartidosController {

    @Autowired
    private PartidosService servicePartidos;

    @GetMapping("/")
    public String inicio(Model model) {

        List<PartidoDTO> resultados = servicePartidos.buscarPartidosDTO(12);
        System.out.println("DTOs recibidos: " + resultados.size());

        model.addAttribute("resultados", resultados);

        return "inicio";
    }


}
