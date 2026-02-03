package com.roy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.roy.model.Emp;
import com.roy.service.IEmpsService;
import com.roy.service.IDeptsService;

@Controller
@RequestMapping(value="/empleados")
public class EmpsController {

    @Autowired
    private IEmpsService serviceEmps;

    @Autowired
    private IDeptsService serviceDepts;

    // Listado paginado (lo que querías de 5 en 5)
    @GetMapping("/index")
    public String mostrarIndex(Model model, Pageable page) {
        Page<Emp> lista = serviceEmps.buscarTodas(page);
        model.addAttribute("empleados", lista); // <-- ¡ESTE NOMBRE ES LA CLAVE!
        return "empleados/listEmpleados";
    }

    // Formulario para crear nuevo empleado
    @GetMapping("/create")
    public String crear(Emp emp, Model model) { // Cambié 'empleado' por 'emp' para que coincida con el HTML
        model.addAttribute("emp", emp);
        model.addAttribute("departamentos", serviceDepts.buscarTodas());
        return "empleados/formEmpleado";
    }

    // Guardar o actualizar empleado
    @PostMapping("/save")
    public String guardar(Emp empleado, BindingResult result, RedirectAttributes attributes, Model model) {
        if (result.hasErrors()){
            model.addAttribute("departamentos", serviceDepts.buscarTodas());
            return "empleados/formEmpleado";
        }
        serviceEmps.guardar(empleado);
        attributes.addFlashAttribute("msg", "¡Datos del empleado guardados correctamente!");
        return "redirect:/empleados/index";
    }

    // Editar empleado
    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int empno, Model model) {
        Emp empleado = serviceEmps.buscarPorNumero(empno);
        model.addAttribute("emp", empleado); // Aquí también pasamos como 'emp'
        model.addAttribute("departamentos", serviceDepts.buscarTodas());
        return "empleados/formEmpleado";
    }

    // Eliminar empleado
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int empno, RedirectAttributes attributes) {
        serviceEmps.eliminar(empno);
        attributes.addFlashAttribute("msg", "Empleado eliminado con éxito.");
        return "redirect:/empleados/index";
    }
}