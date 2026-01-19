package com.roy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.roy.demo.model.Vacante;

@Controller
public class HomeController {

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
		List<Vacante> lista = getVacantes();
		model.addAttribute("vacantes", lista);
		
		return "tabla";
	}
	
	private List<Vacante> getVacantes(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> lista = new LinkedList<Vacante>();
		
		try {
			//Creamos la oferta de trabajo
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setDescripcion("Solicitamos Ing. Civil para ");
			vacante1.setFecha(sdf.parse("08-02-2025"));
			vacante1.setSalario(8500.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
			
			// Vacante 2: Desarrollo de Software
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Desarrollador Java Spring");
			vacante2.setDescripcion("Buscamos experto en backend para desarrollo de microservicios.");
			vacante2.setFecha(sdf.parse("10-02-2026"));
			vacante2.setSalario(12000.0);
			vacante2.setDestacado(1);
			vacante2.setImagen("empresa2.png");

			// Vacante 3: Contabilidad
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Contador Senior");
			vacante3.setDescripcion("Responsable de auditorías mensuales y gestión de impuestos.");
			vacante3.setFecha(sdf.parse("12-02-2026"));
			vacante3.setSalario(9500.0);
			vacante3.setDestacado(0);
			vacante3.setImagen("empresa3.png");

			// Vacante 4: Diseño
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador UX/UI");
			vacante4.setDescripcion("Creación de prototipos y mejora de la experiencia de usuario.");
			vacante4.setFecha(sdf.parse("15-02-2026"));
			vacante4.setSalario(8000.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("empresa1.png");

			// Vacante 5: Marketing
			Vacante vacante5 = new Vacante();
			vacante5.setId(5);
			vacante5.setNombre("Analista de Marketing Digital");
			vacante5.setDescripcion("Gestión de campañas SEM y análisis de métricas en redes sociales.");
			vacante5.setFecha(sdf.parse("18-02-2026"));
			vacante5.setSalario(7500.0);
			vacante5.setDestacado(0);
			vacante5.setImagen("empresa2.png");
			
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			lista.add(vacante5);
			
			
		} catch (Exception e) {
			System.err.println("Upsiii algo ha falladoo");
		}
		return lista;
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
}
