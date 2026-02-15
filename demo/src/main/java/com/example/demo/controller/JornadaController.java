package com.example.demo.controller;

import com.example.demo.model.Jornada;
import com.example.demo.service.IJornadaService;
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
@RequestMapping("/jornadas")
public class JornadaController {

    @Qualifier("jornadaServiceImp")
    @Autowired
    private IJornadaService serviceJornada;

    // @GetMapping("/index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarIndex(Model model){
        List<Jornada> lista = serviceJornada.buscarTodas();
        model.addAttribute("jornada", lista);
        return "jornadas/listJornadas";
    }

    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page){
        Page<Jornada> lista = serviceJornada.buscarTodas((org.springframework.data.domain.Pageable) page);
        model.addAttribute("jornada", lista);
        return "jornadas/listJornadas";
    }

    @GetMapping("/listJornadas")
    public String listarJornadas(Model model){
        List<Jornada> lista = serviceJornada.buscarTodas();
        model.addAttribute("jornada", lista);
        return "jornadas/listJornadas";
    }

    // GetMapping("/create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String crear(Model model){
        model.addAttribute("jornada", new Jornada());
        return "jornadas/formJornadas";
    }

    // @PostMapping("/save")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String guardar(Jornada jornada, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            System.out.println("Existen errores");
            return "jornadas/formJornadas";
        }

        // Guardamos el objeto categoria en la bd
        serviceJornada.guardar(jornada);
        attributes.addFlashAttribute("msg", "Los datos guardados");
        return "redirect:/jornadas/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Integer idJornada, Model model){
        Jornada jornada = serviceJornada.buscarPorId(idJornada);
        model.addAttribute("jornada", jornada);
        return "jornadas/formJornadas";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idJornada, RedirectAttributes attributes) {
        //Eliminamos la categoria
        serviceJornada.eliminar(idJornada);
        attributes.addFlashAttribute("msg", "El equipo fue eliminado!.");
        return "redirect:/jornadas/listJornadas";
    }

}
