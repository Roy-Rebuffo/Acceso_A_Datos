package com.example.demo.controller;

import com.example.demo.model.Equipo;
import com.example.demo.service.IEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    @Qualifier("equipoServiceImp")
    @Autowired
    private IEquipoService serviceEquipo;

    // @GetMapping("/index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarIndex(Model model){
        List<Equipo> lista = serviceEquipo.buscarTodas();
        model.addAttribute("equipo", lista);
        return "equipos/listEquipos";
    }

    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page){
        Page<Equipo> lista = serviceEquipo.buscarTodas((org.springframework.data.domain.Pageable) page);
        model.addAttribute("equipo", lista);
        return "equipos/listEquipos";
    }

    @GetMapping("/listEquipos")
    public String listarEquipos(Model model){
        List<Equipo> lista = serviceEquipo.buscarTodas();
        model.addAttribute("equipo", lista);
        return "equipos/listEquipos";
    }

    // GetMapping("/create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String crear(Model model){
        model.addAttribute("equipo", new Equipo());
        return "equipos/formEquipos";
    }

    // @PostMapping("/save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String guardar(Equipo equipo, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            System.out.println("Existen errores");
            return "equipos/formEquipos";
        }

        // Guardamos el objeto categoria en la bd
        serviceEquipo.guardar(equipo);
        attributes.addFlashAttribute("msg", "Los datos guardados");
        return "redirect:/equipos/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Integer idEquipo, Model model){
        Equipo equipo = serviceEquipo.buscarPorId(idEquipo);
        model.addAttribute("equipo", equipo);
        return "equipos/formEquipos";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idEquipo, RedirectAttributes attributes) {
        //Eliminamos la categoria
        serviceEquipo.eliminar(idEquipo);
        attributes.addFlashAttribute("msg", "El equipo fue eliminado!.");
        return "redirect:/equipos/listEquipos";
    }

}
