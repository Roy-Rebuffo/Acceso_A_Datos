package com.roy.service;

import java.util.LinkedList;
import java.util.List;

import com.roy.model.Departamento;

public abstract class DepartamentosServiceImpl implements IDepartamentosService {
	private List<Departamento> lista = null;
	
	public DepartamentosServiceImpl() {
	    // Lista de departamentos
	    lista = new LinkedList<Departamento>();

	    try {
	        // Departamento 1
	        Departamento dep1 = new Departamento();
	        dep1.setDeptNo(10);
	        dep1.setdName("CONTABILIDAD");
	        dep1.setLoc("MADRID");

	        // Departamento 2
	        Departamento dep2 = new Departamento();
	        dep2.setDeptNo(20);
	        dep2.setdName("INVESTIGACIÓN");
	        dep2.setLoc("BARCELONA");

	        // Departamento 3
	        Departamento dep3 = new Departamento();
	        dep3.setDeptNo(30);
	        dep3.setdName("VENTAS");
	        dep3.setLoc("VALENCIA");

	        // Departamento 4
	        Departamento dep4 = new Departamento();
	        dep4.setDeptNo(40);
	        dep4.setdName("OPERACIONES");
	        dep4.setLoc("SEVILLA");

	        // Departamento 5
	        Departamento dep5 = new Departamento();
	        dep5.setDeptNo(50);
	        dep5.setdName("RECURSOS HUMANOS");
	        dep5.setLoc("BILBAO");

	        // Departamento 6
	        Departamento dep6 = new Departamento();
	        dep6.setDeptNo(60);
	        dep6.setdName("MARKETING");
	        dep6.setLoc("MÁLAGA");

	        /**
	         * Agregamos los 6 objetos de tipo Departamento a la lista
	         */
	        lista.add(dep1);
	        lista.add(dep2);
	        lista.add(dep3);
	        lista.add(dep4);
	        lista.add(dep5);
	        lista.add(dep6);

	    } catch (Exception e) {
	        System.out.println("Error al crear departamentos: " + e.getMessage());
	    }
	}
	
	@Override
	public List<Departamento> buscarTodas() {
		return lista;
	}

	public Departamento buscarPorNumero(Integer deptNo) {
		for (Departamento d : lista) {
			if (d.getDeptNo() == deptNo) {
				return d;
			}
		}
		
		return null;
	}

	@Override
	public void guardar(Departamento departamento) {
		lista.add(departamento);
	}

}
