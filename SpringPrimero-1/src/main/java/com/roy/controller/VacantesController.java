package com.roy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.roy.service.iVacantesService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	//Inyección de dependencia
	@Autowired
	private iVacantesService serviceVacantes;
	
	@GetMapping("/delete/{id}")
	public String eliminar(
			@PathVariable("id") int idVacante, Model model) {
		
		String mensaje = "";
		if(serviceVacantes.borrarPorId(idVacante)) {
			mensaje = "Borrando vacante con id: " + idVacante;
		}else {
			mensaje = "No se ha podido borrar vacante con id: " + idVacante;
		}
		System.out.println("Borrando vacante con id: " + idVacante);
		model.addAttribute("mensaje", mensaje);
		
		return "vacantes/mensaje";
	}
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		model.addAttribute("vacantes", serviceVacantes.buscarTodas());
		
		return "vacantes/tabla";
	}
		
}
