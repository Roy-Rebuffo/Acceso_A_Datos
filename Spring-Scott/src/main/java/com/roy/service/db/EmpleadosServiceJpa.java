package com.roy.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.roy.model.Emp;
import com.roy.repository.EmpleadosRepository;
import com.roy.service.IEmpleadosService;

@Service
public class EmpleadosServiceJpa implements IEmpleadosService {

	@Autowired
	private EmpleadosRepository empleadosRepo;

	@Override
	public List<Emp> buscarTodas() {
		return empleadosRepo.findAll();
	}

	@Override
	public Emp buscarPorId(Integer empNo) {
		Optional<Emp> optional = empleadosRepo.findById(empNo);
		if(optional.isPresent()) return optional.get();
		return null;
	}

	@Override
	public void guardar(Emp empleado) {
		empleadosRepo.save(empleado);
	}

	public void eliminar(Integer empNo) {
		empleadosRepo.deleteById(empNo);
	}

	public Page<Emp> buscarTodas(Pageable page) {
		return empleadosRepo.findAll(page);
	}

}
