package com.roy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roy.model.Dept;
import com.roy.service.IDeptsService;

@Controller
@RequestMapping(value="/departamentos")
public class DeptsController {

    @Autowired
    private IDeptsService serviceDepts;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        model.addAttribute("departamentos", serviceDepts.buscarTodas());
        return "departamentos/listDepartamentos";
    }

    @GetMapping("/create")
    public String crear(Dept dept) {
        return "departamentos/formDepartamento";
    }

    @PostMapping("/save")
    public String guardar(Dept dept, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()){
            return "departamentos/formDepartamento";
        }
        serviceDepts.guardar(dept);
        attributes.addFlashAttribute("msg", "¡Departamento guardado con éxito!");
        return "redirect:/departamentos/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int deptno, Model model) {
        Dept dept = serviceDepts.buscarPorNumero(deptno);
        model.addAttribute("dept", dept);
        return "departamentos/formDepartamento";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int deptno, RedirectAttributes attributes) {
        serviceDepts.eliminar(deptno);
        attributes.addFlashAttribute("msg", "Departamento eliminado.");
        return "redirect:/departamentos/index";
    }
}