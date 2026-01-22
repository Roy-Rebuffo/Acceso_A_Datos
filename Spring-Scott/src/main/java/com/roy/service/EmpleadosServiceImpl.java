package com.roy.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.roy.model.Empleado;

public class EmpleadosServiceImpl implements IEmpleadosService {
	
	private List<Empleado> lista = null;
	
	public EmpleadosServiceImpl() {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    lista = new LinkedList<Empleado>();
	    
	    try {
	        // Empleado 1
	        Empleado emp1 = new Empleado();
	        emp1.setEmpNo(7369);
	        emp1.seteName("SÁNCHEZ");
	        emp1.setJob("EMPLEADO");
	        emp1.setMgr(7902);
	        emp1.setHireDate(sdf.parse("17-12-2020"));
	        emp1.setSal(10400.0);
	        emp1.setComm(null); // No tiene comisión
	        emp1.setDeptNo(20);
	        
	        // Empleado 2
	        Empleado emp2 = new Empleado();
	        emp2.setEmpNo(7499);
	        emp2.seteName("ARROYO");
	        emp2.setJob("VENDEDOR");
	        emp2.setMgr(7698);
	        emp2.setHireDate(sdf.parse("20-02-2021"));
	        emp2.setSal(15000.0);
	        emp2.setComm(300.0);
	        emp2.setDeptNo(30);
	        
	        // Empleado 3
	        Empleado emp3 = new Empleado();
	        emp3.setEmpNo(7521);
	        emp3.seteName("SALA");
	        emp3.setJob("VENDEDOR");
	        emp3.setMgr(7698);
	        emp3.setHireDate(sdf.parse("22-02-2021"));
	        emp3.setSal(12500.0);
	        emp3.setComm(500.0);
	        emp3.setDeptNo(30);
	        
	        // Empleado 4
	        Empleado emp4 = new Empleado();
	        emp4.setEmpNo(7566);
	        emp4.seteName("JIMÉNEZ");
	        emp4.setJob("DIRECTOR");
	        emp4.setMgr(7839);
	        emp4.setHireDate(sdf.parse("02-04-2021"));
	        emp4.setSal(29000.0);
	        emp4.setComm(null);
	        emp4.setDeptNo(20);
	        
	        // Empleado 5
	        Empleado emp5 = new Empleado();
	        emp5.setEmpNo(7654);
	        emp5.seteName("MARTÍN");
	        emp5.setJob("VENDEDOR");
	        emp5.setMgr(7698);
	        emp5.setHireDate(sdf.parse("28-09-2021"));
	        emp5.setSal(12500.0);
	        emp5.setComm(1400.0);
	        emp5.setDeptNo(30);
	        
	        // Empleado 6
	        Empleado emp6 = new Empleado();
	        emp6.setEmpNo(7782);
	        emp6.seteName("CEREZO");
	        emp6.setJob("DIRECTOR");
	        emp6.setMgr(7839);
	        emp6.setHireDate(sdf.parse("09-06-2021"));
	        emp6.setSal(24500.0);
	        emp6.setComm(null);
	        emp6.setDeptNo(10);

	        // Agregamos los 6 empleados a la lista
	        lista.add(emp1);
	        lista.add(emp2);
	        lista.add(emp3);
	        lista.add(emp4);
	        lista.add(emp5);
	        lista.add(emp6);

	    } catch (Exception e) {
	        System.out.println("Error al crear departamentos: " + e.getMessage());
	    }
	}

	@Override
	public List<Empleado> buscarTodas() {
		return lista;
	}

	@Override
	public Empleado buscarPorId(Integer empNo) {
		for (Empleado e : lista) {
			if (e.getEmpNo() == empNo) {
				return e;
			}
		}
		
		return null;
	}

	@Override
	public void guardar(Empleado empleado) {
		lista.add(empleado);

	}

}
