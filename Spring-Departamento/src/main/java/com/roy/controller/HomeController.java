package com.roy.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.roy.demo.model.Departamento;


@Controller
public class HomeController {
	
	@GetMapping("/")
	public String mostrarTabla(Model model) {
		List<Departamento> lista = getDepartamentos();
		model.addAttribute("departamentos", lista);
		
		return "home";
	}

	public List<Departamento> getDepartamentos() {
		List<Departamento> lista = new LinkedList<Departamento>();
		
		try {
			
			Departamento d1 = new Departamento();
			d1.setId(1);
			d1.setNombre("Desarrollo Software");
			d1.setLoc("Madrid");
			d1.setImagen("empresa1.png");
			
			// Departamento 2
			Departamento d2 = new Departamento();
			d2.setId(2);
			d2.setNombre("Marketing y Ventas");
			d2.setLoc("Barcelona");
			d2.setImagen("empresa2.png");

			// Departamento 3
			Departamento d3 = new Departamento();
			d3.setId(3);
			d3.setNombre("Recursos Humanos");
			d3.setLoc("Sevilla");
			d3.setImagen("empresa3.png");
		
			// Departamento 4
			Departamento d4 = new Departamento();
			d4.setId(4);
			d4.setNombre("Sistemas y Redes");
			d4.setLoc("Valencia");
			d4.setImagen("empresa1.png");

			// Departamento 5
			Departamento d5 = new Departamento();
			d5.setId(5);
			d5.setNombre("Atención al Cliente");
			d5.setLoc("Bilbao");
			d5.setImagen("empresa2.png");
			
			lista.add(d1);
			lista.add(d2);
			lista.add(d3);
			lista.add(d4);
			lista.add(d5);
		} catch (Exception e) {
			System.err.println("upsii errorr");
		}
		
		return lista;
	}
	
	//Modelo que sea un nº entero, le pasamos al template el numero y 
	//que ponga a tabla de multiplicar de ese nº
	@GetMapping("/mult/{id}")
	public String tablaMult(
			@PathVariable("id") int numero, Model model) {
		
		List<Integer> lista = new ArrayList<Integer>();
		
		for (int i = 0; i <= 10; i++) lista.add(numero * i);
		
		model.addAttribute("lista",lista);
		model.addAttribute("numero",numero);
		return "mult";
	}
	
}
