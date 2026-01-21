package com.roy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.roy.demo.model.Vacante;
import com.roy.service.iVacantesService;

@Controller
public class HomeController {
	
	//Inyección de dependencia
	@Autowired
	private iVacantesService serviceVacantes;

	@GetMapping("/")
	public String mostrarHome(Model model) {
		String nombre = "Programador";
		Date fecha = new Date();
		double salario = 9000;
		boolean vigente = true;
		
		model.addAttribute("nombre", nombre);
		model.addAttribute("fecha", fecha);
		model.addAttribute("salario", salario);
		model.addAttribute("vigente", vigente);
		return "home";
	}
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		model.addAttribute("vacantes", serviceVacantes.buscarTodas());
		
		return "tabla";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero de Sistemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista);
		
		return "listado";
		
	}
	
	@GetMapping("/buscar/{id}")
	public String buscarPorId(
			@PathVariable("id") int n, Model model) {
		
		Vacante v = serviceVacantes.buscarPorId(n);
		
		model.addAttribute("vacante",v);
		return "buscar";
	}
}
